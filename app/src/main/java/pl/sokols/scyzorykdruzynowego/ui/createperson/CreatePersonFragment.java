package pl.sokols.scyzorykdruzynowego.ui.createperson;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import pl.sokols.scyzorykdruzynowego.R;
import pl.sokols.scyzorykdruzynowego.databinding.FragmentCreatePersonBinding;

import static android.content.DialogInterface.BUTTON_NEGATIVE;

public class CreatePersonFragment extends Fragment {

    private CreatePersonViewModel viewModel;
    private FragmentCreatePersonBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CreatePersonViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_person, container, false);
        binding.setCreatePersonViewModel(viewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        initObserversAndListeners(view);
        return view;
    }

    private void initObserversAndListeners(View view) {
        viewModel.getPerson().observe(getViewLifecycleOwner(), person -> {
            if (person.getName() == null) {
                binding.nameNewPersonTextInputLayout.setError(getString(R.string.required_error));
            } else {
                binding.nameNewPersonTextInputLayout.setError(null);
            }

            if (person.getSurname() == null) {
                binding.surnameNewPersonTextInputLayout.setError(getString(R.string.required_error));
            } else {
                binding.surnameNewPersonTextInputLayout.setError(null);
            }
        });

        viewModel.getIsReadyToAddPerson().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                Snackbar.make(view, getString(R.string.added_new_person_completed), BaseTransientBottomBar.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_new_person_to_people);
            }
        });

        binding.dateNewPersonEditText.setOnClickListener(dateEditTextOnClickListener);
    }

    private View.OnClickListener dateEditTextOnClickListener = v -> {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), R.style.MyDatePickerTheme,
                (view, year, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                    ((EditText) v).setText(selectedDate);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.setButton(BUTTON_NEGATIVE, getString(R.string.cancel), datePickerDialog);
        datePickerDialog.show();
    };
}