package com.usefulio.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;
import android.util.Log;

/**
 * This class performs sum called from JavaScript.
 */
public class ShellCmdPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("run")) {
            
            List<String> list = new ArrayList<String>();
            for(int i=0; i < args.length(); i++) {
               list.add(args.getString(i));
            }
            
            String cmd = String.join(" ", list);
            Log.i("exec method: cmd=", cmd);
            cmd_return = shell_cmd_run(cmd);
            callbackContext.success(cmd_return);
            return true;
        }
        return false;
    }
    
    
    public String shell_cmd_run(String command) {
        StringBuffer output = new StringBuffer();
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = "";
            while (true) {
                str = reader.readLine();
                if (str == null) {
                    break;
                }
                output.append(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    
    
    
    
}
