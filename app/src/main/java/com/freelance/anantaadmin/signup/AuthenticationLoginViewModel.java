package com.freelance.anantaadmin.signup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthenticationLoginViewModel extends ViewModel {
    private MutableLiveData<Authentication> authenticationLiveData = new MutableLiveData<>();


    public void authentication(String email) {
        AuthenticationRepo.getInstance().authentication(email, authenticationLiveData);
    }

    public MutableLiveData<Authentication> authenticationLiveData(){
        return  authenticationLiveData;
    }
}
