package org.aml.apigatewayimporter;

import java.io.File;
import java.io.FileNotFoundException;

import org.aml.apimodel.Api;
import org.aml.apimodel.TopLevelModel;
import org.aml.typesystem.ramlreader.TopLevelRamlModelBuilder;

import com.amazonaws.services.apigateway.model.ImportRestApiResult;

public class Main {

	public static void main(String[] args) {
		TopLevelModel build;
		try {
			if (args.length<1){
				System.err.println("Please specify path to api");
				return;
			}
			build = TopLevelRamlModelBuilder.build(new File(args[0]));
			try{
			ImportRestApiResult doImport = new ApiImporter(null,null,null).doImport((Api) build);
			System.out.println("Successfully imported:"+doImport.getId()+"("+doImport.getName()+")");
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.err.println("API file not found:" + args[0]);
		}
	}
}
