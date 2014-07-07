package com.tekusource.superfly.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ckeditor.CKEditorConfig;

public class CKEditorHandler {

	public static CKEditorConfig createConfiguration() {
		CKEditorConfig settings = new CKEditorConfig();
		settings.addConfigValue("width", "600");
		settings.addConfigValue("height", "450");
		settings.addConfigValue("float", "right");
		List<Object> mainList = new ArrayList<Object>();
		HashMap<String, Object> toolbarSectionMap = new HashMap<String, Object>();
		List<String> subList = new ArrayList<String>();
		subList.add("Source");
		subList.add("-");
		subList.add("Save");
		subList.add("Preview");
		subList.add("Print");
		subList.add("Templates");
		subList.add("NewPage");
		toolbarSectionMap.put("name", "document");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("Cut");
		subList.add("Copy");
		subList.add("Paste");
		subList.add("PasteText");
		subList.add("PasteFromWord");
		subList.add("Undo");
		subList.add("Redo");
		toolbarSectionMap.put("name", "clipboard");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("Form");
		subList.add("Checkbox");
		subList.add("Radio");
		subList.add("TextField");
		subList.add("Textarea");
		subList.add("Select");
		subList.add("Button");
		subList.add("ImageButton");
		subList.add("HiddenField");
		toolbarSectionMap.put("name", "forms");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		mainList.add("/");
		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("Styles");
		subList.add("Format");
		subList.add("Font");
		subList.add("FontSize");
		toolbarSectionMap.put("name", "styles");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("Bold");
		subList.add("Italic");
		subList.add("Underline");
		subList.add("Strike");
		subList.add("Subscript");
		subList.add("Superscript");
		subList.add("RemoveFormat");
		toolbarSectionMap.put("name", "basicstyles");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("TextColor");
		subList.add("BGColor");
		toolbarSectionMap.put("name", "colors");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		mainList.add("/");

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("NumberedList");
		subList.add("BulletedList");
		subList.add("-");
		subList.add("Outdent");
		subList.add("Indent");
		subList.add("-");
		subList.add("JustifyLeft");
		subList.add("JustifyCenter");
		subList.add("JustifyRight");
		subList.add("JustifyBlock");
		subList.add("-");
		subList.add("Blockquote");
		subList.add("BidiLtr");
		subList.add("BidiRtl");
		toolbarSectionMap.put("name", "paragraph");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("Image");
		subList.add("Flash");
		subList.add("Table");
		subList.add("HorizontalRule");
		subList.add("Smiley");
		subList.add("SpecialChar");
		subList.add("PageBreak");
		subList.add("Iframe");
		toolbarSectionMap.put("name", "insert");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		toolbarSectionMap = new HashMap<String, Object>();
		subList = new ArrayList<String>();
		subList.add("Find");
		subList.add("Replace");
		subList.add("-");
		subList.add("SelectAll");
		subList.add("SpellChecker");
		toolbarSectionMap.put("name", "editing");
		toolbarSectionMap.put("items", subList);
		mainList.add(toolbarSectionMap);

		settings.addConfigValue("toolbar", mainList);

		return settings;
	}
}
