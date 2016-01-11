package diverse;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Simon on 11/01/16.
 */
public class FirebaseResultHandler implements Firebase.ResultHandler {

    private String errorMessage;
    private boolean success;

    @Override
    public void onSuccess() {
        success = true;
    }

    @Override
    public void onError(FirebaseError firebaseError) {
        errorMessage = firebaseError.getMessage();
        System.out.println(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isCreationSuccessFull() {
        return success;
    }
}
