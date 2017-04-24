package yanews.malygin.tim.yanews.api;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.SparseArray;

import java.util.Collections;
import java.util.Map;

import yanews.malygin.tim.yanews.api.methods.ApiMethod;
import yanews.malygin.tim.yanews.api.methods.DeleteUserMethod;
import yanews.malygin.tim.yanews.api.methods.LoginMethod;
import yanews.malygin.tim.yanews.api.methods.LogoutMethod;
import yanews.malygin.tim.yanews.api.methods.RegistrationMethod;
import yanews.malygin.tim.yanews.idlingresorce.SimpleIdlingResource;

import static yanews.malygin.tim.yanews.api.ApiKeys.DELETE_USER;
import static yanews.malygin.tim.yanews.api.ApiKeys.LOGIN;
import static yanews.malygin.tim.yanews.api.ApiKeys.LOGOUT;
import static yanews.malygin.tim.yanews.api.ApiKeys.REGISTRATION;

/**
 * Created by timofey.malygin on 23/04/2017.
 */
public class Api {

    //region forTestOnly
    @VisibleForTesting
    public static Map<Integer, SimpleIdlingResource> idleResources;
    //endregion

    public static <T extends ApiMethod> T createMethod(@ApiKeys.ApiKey int key){
        final SimpleIdlingResource simpleIdlingResource = idleResources!=null? idleResources.get(key): null;
        switch (key){
            case LOGIN:
                return (T) new LoginMethod(simpleIdlingResource);
            case LOGOUT:
                return (T) new LogoutMethod(simpleIdlingResource);
            case REGISTRATION:
                return (T) new RegistrationMethod(simpleIdlingResource);
            case DELETE_USER:
                return (T) new DeleteUserMethod(simpleIdlingResource);
        }
        throw new RuntimeException("unknown key "+key);
    }
}