package com.codebillionz.android.hng_internship_app;

import com.codebillionz.android.hng_internship_app.Models.AppUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Global {
    static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    static FirebaseUser user = mAuth.getCurrentUser();



//    public AppUser getAppUser() {
////        user = mAuth.getCurrentUser();
////        if(user!=null){
////            return new AppUser(user.getUid(),);
////        };
//
//        return appUser;
//    }
}
