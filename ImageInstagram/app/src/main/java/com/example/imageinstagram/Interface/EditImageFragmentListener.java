package com.example.imageinstagram.Interface;

public interface EditImageFragmentListener {
    void onBrightnessChanged(int brightness);
    void onConstraintChanged(float constraint);
    void onSaturationChanged(float saturation);
    void onEditStarted();
    void onEditCompleted();

}
