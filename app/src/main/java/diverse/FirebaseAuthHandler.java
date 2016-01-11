package diverse;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Simon on 11/01/16.
 */
public class FirebaseAuthHandler implements Firebase.AuthResultHandler {

    private String errorMessage;
    private String id;
    private boolean success;

    @Override
    public void onAuthenticated(AuthData authData) {
        success = true;
        id = authData.getUid();
        System.out.println("Logged in. UserID = " + authData.getUid());
    }

    @Override
    public void onAuthenticationError(FirebaseError firebaseError) {
        success = false;
        errorMessage = firebaseError.getMessage();
        System.out.println(firebaseError.getMessage());
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getId() {
        return id;
    }

    public boolean isAuthSuccessfull() {
        return success;
    }
}
