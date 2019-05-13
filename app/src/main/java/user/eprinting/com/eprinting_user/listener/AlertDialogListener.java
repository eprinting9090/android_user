package user.eprinting.com.eprinting_user.listener;

import android.app.Dialog;

public class AlertDialogListener {
    public interface TwoButton {
        void onClickButtonLeft(Dialog dialog);
        void onClickButtonRight(Dialog dialog);
    }

    public interface OneButton {
        void onClickButton(Dialog dialog);
    }
}
