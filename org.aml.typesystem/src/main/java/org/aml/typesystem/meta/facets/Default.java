package org.aml.typesystem.meta.facets;

import org.aml.typesystem.AbstractType;
import org.aml.typesystem.BuiltIns;
import org.aml.typesystem.ITypeRegistry;
import org.aml.typesystem.Status;

public class Default extends Facet<Object>{

	public Default(Object value) {
		super(value);
	}

	@Override
	public String facetName() {
		return "default";
	}

	@Override
	public Status validate(ITypeRegistry registry) {
		return ownerType.validate(value);
	}

	@Override
	public AbstractType requiredType() {
		return BuiltIns.ANY;
	}

}
