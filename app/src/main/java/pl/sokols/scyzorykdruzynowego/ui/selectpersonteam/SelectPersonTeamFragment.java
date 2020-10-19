package pl.sokols.scyzorykdruzynowego.ui.selectpersonteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.sokols.scyzorykdruzynowego.R;

public class SelectPersonTeamFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_person_team, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.newPersonButton)
    public void setNewPersonButton() {
        Navigation.findNavController(requireView()).navigate(R.id.action_select_to_new_person);
    }

    @OnClick(R.id.newTeamButton)
    public void setNewTeamButton() {
        Navigation.findNavController(requireView()).navigate(R.id.action_select_to_new_team);
    }
}