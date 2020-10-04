package pl.sokols.scyzorykdruzynowego.activities.data.reopistories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pl.sokols.scyzorykdruzynowego.activities.data.AppDatabase;
import pl.sokols.scyzorykdruzynowego.activities.data.dao.UserDao;
import pl.sokols.scyzorykdruzynowego.activities.data.entities.User;

public class UserViewModel extends AndroidViewModel {

    private ExecutorService executorService;
    private UserDao userDao;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDao = AppDatabase.getInstance(application).userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getItemByName(String login) {
        return userDao.getItemByName(login);
    }

    public int checkItemByName(String login) {
        return userDao.checkItemByName(login);
    }
}