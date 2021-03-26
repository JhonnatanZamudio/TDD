package com.jhonnatan.tdd.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import com.jhonnatan.tdd.data.LoginRepository;
import com.jhonnatan.tdd.data.Result;
import com.jhonnatan.tdd.data.model.LoggedInUser;
import com.jhonnatan.tdd.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(validarCampos(username,password))));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public String validarCampos(String username, String password) {
        if (username.equals("")){
            return "";
        } else {
            return null;
        }
    }

    public void loginDataChanged(String username, String password) {
        loginFormState.setValue(new LoginFormState(true));
    }
}