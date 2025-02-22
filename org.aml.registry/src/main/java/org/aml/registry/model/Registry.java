package org.aml.registry.model;

import java.util.ArrayList;
import java.util.List;

import org.aml.apimodel.TopLevelModel;
import org.aml.registry.operations.StoreRegistry;
import org.aml.typesystem.ramlreader.TopLevelRamlModelBuilder;

public class Registry {

	protected String name="Registry";
	protected String title="Registry";
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	protected String version="v1";
	
	protected ArrayList<ApiDescription> apis=new ArrayList<ApiDescription>();
	protected ArrayList<ToolDescription> tools=new ArrayList<ToolDescription>();
	public ArrayList<ToolDescription> getTools() {
		return tools;
	}
	public void setTools(ArrayList<ToolDescription> tools) {
		this.tools = tools;
	}
	protected ArrayList<LibraryDescription> libraries=new ArrayList<LibraryDescription>();
	protected ArrayList<SubRegistryDescription>includes=new ArrayList<SubRegistryDescription>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public ArrayList<ApiDescription> getApis() {
		return apis;
	}
	public void setApis(ArrayList<ApiDescription> apis) {
		this.apis = apis;
	}
	public ArrayList<LibraryDescription> getLibraries() {
		return libraries;
	}
	public void setLibraries(ArrayList<LibraryDescription> libraries) {
		this.libraries = libraries;
	}
	public ArrayList<SubRegistryDescription> getIncludes() {
		return includes;
	}
	public void setIncludes(ArrayList<SubRegistryDescription> includes) {
		this.includes = includes;
	}
	
	public List<ItemDescription>items(){
		ArrayList<ItemDescription>result=new ArrayList<ItemDescription>();
		result.addAll(libraries);
		result.addAll(apis);
		return result;
	}
	
	public String asString(){
		return new StoreRegistry().apply(this);
	}
	public void append(Registry apply) {
		apply.apis.forEach(x->{
			this.apis.add(x.clone());
		});
		apply.libraries.forEach(x->{
			this.libraries.add(x.clone());
		});		
		this.includes.addAll(apply.includes);
	}
	
	public Overlays getOverlays(String string) {
		for (ApiDescription d:apis){
			if (d.name.replace(' ', '_').equals(string)){
				if (d.originalLocation!=null){
					return OverlaysProvider.getOverlaysFor(d.originalLocation.replace('\\', '/'));
				}
				return OverlaysProvider.getOverlaysFor(d.location);
			}
		}
		return null;
	}
	public TopLevelModel getApi(String string){
		for (ApiDescription d:apis){
			if (d.name.replace(' ', '_').equals(string)){
				return d.resolve();
			}
		}
		return null;
	}
	
	public TopLevelModel getOverlayed(String string,String libraryId){
		Overlays overlays = getOverlays(string);
		if (overlays!=null){
			String string2 = overlays.getOverlaysFor().get(libraryId);
			if (string2!=null){
				return TopLevelRamlModelBuilder.build(string2);
			}
		}
		return getApi(string);
	}
}
