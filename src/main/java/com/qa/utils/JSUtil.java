package com.qa.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/11.
 */
public class JSUtil {
   static ScriptEngineManager manager = new ScriptEngineManager();
  public static ScriptEngine engine = manager.getEngineByName("javascript");
}
