package net.kdt.pojavlaunch.customcontrols;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;
import net.kdt.pojavlaunch.TranslateableArrayAdapter;

import java.util.ArrayList;

import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.ActuationMode.HIDE_ON_GRAB_ENABLED;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.ActuationMode.NONE;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.ActuationMode.SHOW_ON_GRAB_ENABLED;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.Orientation.DOWN;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.Orientation.LEFT;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.Orientation.RIGHT;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.Orientation.UP;
import static net.kdt.pojavlaunch.customcontrols.ControlDrawerData.Orientation.FREE;

@androidx.annotation.Keep
public class ControlDrawerData {

    public final ArrayList<ControlData> buttonProperties;
    public final ControlData properties;
    public Orientation orientation;
    public ActuationMode actuationMode = NONE;

    @androidx.annotation.Keep
    public enum Orientation {
        DOWN,
        LEFT,
        UP,
        RIGHT,
        FREE
    }
    @androidx.annotation.Keep
    public enum ActuationMode implements TranslateableArrayAdapter.Translateable {
        NONE(R.string.customctrl_auto_actuation_none),
        SHOW_ON_GRAB_ENABLED(R.string.customctrl_auto_actuation_show_ingame),
        HIDE_ON_GRAB_ENABLED(R.string.customctrl_auto_actuation_hide_ingame);
        public final int modeNameString;
        ActuationMode(int modeNameString) {
            this.modeNameString = modeNameString;
        }

        @Override
        public int getTranslationString() {
            return modeNameString;
        }
    }

    public static Orientation[] getOrientations(){
        return new Orientation[]{DOWN,LEFT,UP,RIGHT,FREE};
    }

    public static int orientationToInt(Orientation orientation){
        switch (orientation){
            case DOWN: return 0;
            case LEFT: return 1;
            case UP: return 2;
            case RIGHT: return 3;
            case FREE: return 4;
        }
        return -1;
    }

    public static Orientation intToOrientation(int by){
        switch (by){
            case 0: return DOWN;
            case 1: return LEFT;
            case 2: return UP;
            case 3: return RIGHT;
            case 4: return FREE;
        }
        return null;
    }

    public static ActuationMode[] getActuationModes() {
        return new ActuationMode[] {NONE, SHOW_ON_GRAB_ENABLED, HIDE_ON_GRAB_ENABLED};
    }

    public static int actuationModeToInt(ActuationMode actuationMode) {
        switch(actuationMode) {
            case NONE: return 0;
            case SHOW_ON_GRAB_ENABLED: return 1;
            case HIDE_ON_GRAB_ENABLED: return 2;
        }
        return -1;
    }

    public static ActuationMode intToActuationMode(int by) {
        switch (by) {
            case 0: return NONE;
            case 1: return SHOW_ON_GRAB_ENABLED;
            case 2: return HIDE_ON_GRAB_ENABLED;
        }
        return null;
    }

    public ControlDrawerData(){
        this(new ArrayList<>());
    }

    public ControlDrawerData(ArrayList<ControlData> buttonProperties){
        this(buttonProperties, new ControlData("Drawer", new int[] {}, Tools.currentDisplayMetrics.widthPixels/2f, Tools.currentDisplayMetrics.heightPixels/2f));
    }

    public ControlDrawerData(ArrayList<ControlData> buttonProperties, ControlData properties){
        this(buttonProperties, properties, Orientation.LEFT);
    }


    public ControlDrawerData(ArrayList<ControlData> buttonProperties, ControlData properties, Orientation orientation){
        this.buttonProperties = buttonProperties;
        this.properties = properties;
        this.orientation = orientation;
    }

    public ControlDrawerData(ControlDrawerData drawerData){
        buttonProperties = new ArrayList<>(drawerData.buttonProperties.size());
        for(ControlData controlData : drawerData.buttonProperties){
            buttonProperties.add(new ControlData(controlData));
        }
        properties = new ControlData(drawerData.properties);
        orientation = drawerData.orientation;
        actuationMode = drawerData.actuationMode;
    }

}
