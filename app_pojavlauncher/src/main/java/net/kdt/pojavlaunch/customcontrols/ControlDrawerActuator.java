package net.kdt.pojavlaunch.customcontrols;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import net.kdt.pojavlaunch.GrabListener;
import net.kdt.pojavlaunch.Tools;
import net.kdt.pojavlaunch.customcontrols.buttons.ControlDrawer;

import org.lwjgl.glfw.CallbackBridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlDrawerActuator implements GrabListener {
    private static final List<ControlDrawer> EMPTY_DRAWER_LIST = Collections.emptyList();
    private final ArrayMap<ControlDrawerData.ActuationMode, List<ControlDrawer>> mActuatedDrawers;

    public ControlDrawerActuator() {
        mActuatedDrawers = new ArrayMap<>();
        mActuatedDrawers.put(ControlDrawerData.ActuationMode.SHOW_ON_GRAB_ENABLED, new ArrayList<>());
        mActuatedDrawers.put(ControlDrawerData.ActuationMode.HIDE_ON_GRAB_ENABLED, new ArrayList<>());
    }

    public void unregisterAllDrawers() {
        for(ControlDrawerData.ActuationMode mode : ControlDrawerData.getActuationModes()) {
            List<ControlDrawer> actuatedDrawersList = mActuatedDrawers.get(mode);
            if(actuatedDrawersList == null) continue;
            actuatedDrawersList.clear();
        }
    }
    
    public void registerControlDrawer(ControlDrawer controlDrawer) {
        ControlDrawerData.ActuationMode actuationMode = controlDrawer.drawerData.actuationMode;
        List<ControlDrawer> drawerList = mActuatedDrawers.get(actuationMode);
        if(drawerList == null) return;
        drawerList.add(controlDrawer);
    }

    private @NonNull List<ControlDrawer> getDrawerListForMode(ControlDrawerData.ActuationMode mode) {
        List<ControlDrawer> drawerList = mActuatedDrawers.get(mode);
        if(drawerList == null) drawerList = EMPTY_DRAWER_LIST;
        return drawerList;
    }

    private void processGrabOnUiThread(boolean grabState) {
        for(ControlDrawer drawer : getDrawerListForMode(ControlDrawerData.ActuationMode.SHOW_ON_GRAB_ENABLED)) {
            drawer.setButtonVisibility(grabState);
        }
        for(ControlDrawer drawer : getDrawerListForMode(ControlDrawerData.ActuationMode.HIDE_ON_GRAB_ENABLED)) {
            drawer.setButtonVisibility(!grabState);
        }
    }

    public void registerListener() {
        CallbackBridge.addGrabListener(this);
    }

    public void unregisterListener() {
        CallbackBridge.removeGrabListener(this);
    }

    @Override
    public void onGrabState(boolean isGrabbing) {
        Tools.runOnUiThread(()->processGrabOnUiThread(isGrabbing));
    }
}
