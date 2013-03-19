package com.siriusif;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.model.Good;
import com.siriusif.model.Group;

/**
 * This console app present here only for quick and dirty testing
 *
 */
public class App {
	private static Logger LOGGER = Logger.getLogger(App.class);
	public static Group[] groups;
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		LOGGER.info("Import started.");
		groups = Helper.fromJson("/grouplist.json",Group[].class);
		for(Group group : groups){
			group.fixReferencesToParentGroup();
		}
		LOGGER.info("Import allready.");
		for (int i=0; i< groups.length; i++){
			Group currentGroup = groups[i];
			LOGGER.info("Група "+i+" : " + currentGroup.getgName());
			printGoodsOfGroup(currentGroup);
			printSubgroupsAndTheirGoods(currentGroup);
		}
		
//		Group currentGroup = groups[1];
//		LOGGER.info("Група : " + currentGroup.getgName());
//		printSubgroupsAndTheirGoods(currentGroup);
	}

	private static void printSubgroupsAndTheirGoods(Group currentGroup) {
		for(int s = 0; s < currentGroup.getSubGroups().size(); s++){
			Group currentSubGroup = currentGroup.getSubGroups().get(s);
			LOGGER.info(currentSubGroup.getParentGroup().getgName() + ">>" + currentSubGroup.getgName());
			printSubgroupsAndTheirGoods(currentSubGroup);
			printGoodsOfGroup(currentSubGroup);
		}
	}

	private static void printGoodsOfGroup(Group currentGroup) {
		for(int g = 0; g < currentGroup.getGoods().size(); g++){
			Good currentGood =currentGroup.getGoods().get(g);
			LOGGER.info(currentGood.getParentGroup().getgName()+":" + currentGood.getName());
		}
	}
	
}
