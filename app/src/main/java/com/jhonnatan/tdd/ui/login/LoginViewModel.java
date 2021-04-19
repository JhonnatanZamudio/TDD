package com.jhonnatan.tdd.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jhonnatan.tdd.R;
import com.jhonnatan.tdd.data.LoginRepository;
import com.jhonnatan.tdd.data.Result;
import com.jhonnatan.tdd.data.model.LoggedInUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            loginResult.setValue(new LoginResult(new LoggedInUserView(validarCampos(username, password))));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public String validarCampos(String username, String password) {
        if (username.isEmpty()) {
            return "";
        } else if (username.length() < 8) {
            return "El campo Email NO puede tener menos de 8 caracteres";
        } else if (validarEmail(username)) {
            if (password.isEmpty()) {
                return null;
            } else if (password.length() < 5) {
                return "El campo Password  NO puede tener menos de 5 caracteres";
            }
            return null;
        } else {
            return "El Email es incorrecto";
        }

    }

    private boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public void loginDataChanged(String username, String password) {
        loginFormState.setValue(new LoginFormState(true));
    }
}