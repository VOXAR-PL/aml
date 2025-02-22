package org.aml.apimodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.aml.apimodel.INamedParam;
import org.aml.apimodel.MimeType;
import org.aml.apimodel.Response;
import org.aml.typesystem.AbstractType;

public class ResponseImpl extends AnnotableImpl implements Response{

	protected String code;
	protected String description;
	protected ArrayList<INamedParam>headers=new ArrayList<>();
	protected ArrayList<MimeType>mimeType=new ArrayList<>();
	
	public ResponseImpl(String string) {
		this.code=string;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public List<INamedParam> headers() {
		return headers;
	}

	@Override
	public List<MimeType> body() {
		return mimeType;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public boolean hasBody() {
		return !this.body().isEmpty();
	}
	
	

	public void setDescription(String description) {
		this.description=description;
	}

	public void setResponseTypePayload(AbstractType createType) {
		for (MimeType m:mimeType){
			MimeTypeImpl impl=(MimeTypeImpl) m;
			impl.model=createType;
		}
	}

	

}
