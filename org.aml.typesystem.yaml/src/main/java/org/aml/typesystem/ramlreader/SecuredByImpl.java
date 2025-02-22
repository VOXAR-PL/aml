package org.aml.typesystem.ramlreader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.aml.apimodel.Annotable;
import org.aml.apimodel.SecuredByConfig;
import org.aml.apimodel.SecurityScheme;
import org.aml.apimodel.TopLevelModel;
import org.raml.v2.internal.impl.commons.nodes.ParametrizedSecuritySchemeRefNode;
import org.raml.v2.internal.impl.commons.nodes.SecuritySchemeRefNode;
import org.raml.yagi.framework.nodes.Node;

public class SecuredByImpl extends AbstractWrappedNodeImpl<Annotable, Node> implements SecuredByConfig {

	public SecuredByImpl(TopLevelModel mdl, Annotable parent, Node node) {
		super(mdl, parent, node);

	}

	@Override
	public String name() {
		if (this.original instanceof SecuritySchemeRefNode) {
			SecuritySchemeRefNode n = (SecuritySchemeRefNode) this.original;
			String refName = n.getRefName();
			try {
				int parseInt = Integer.parseInt(refName);
				List<SecurityScheme> securityDefinitions = mdl.securityDefinitions();
				return securityDefinitions.get(parseInt).name();
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
			return refName;
		}
		ParametrizedSecuritySchemeRefNode n = (ParametrizedSecuritySchemeRefNode) this.original;
		String refName = n.getRefName();
		try {
			int parseInt = Integer.parseInt(refName);
			List<SecurityScheme> securityDefinitions = mdl.securityDefinitions();
			return securityDefinitions.get(parseInt).name();
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return refName;
	}

	@Override
	public LinkedHashMap<String, Object> settings() {
		if (this.original instanceof SecuritySchemeRefNode) {
			SecuritySchemeRefNode n = (SecuritySchemeRefNode) this.original;
			Map<String, Node> parameters = n.getParameters();
			LinkedHashMap<String, Object> res = new LinkedHashMap<>();
			for (String s : parameters.keySet()) {
				res.put(s, TopLevelRamlModelBuilder.toObject(parameters.get(s)));
			}
			return res;
		}
		ParametrizedSecuritySchemeRefNode n = (ParametrizedSecuritySchemeRefNode) this.original;
		Map<String, Node> parameters = n.getParameters();
		LinkedHashMap<String, Object> res = new LinkedHashMap<>();
		for (String s : parameters.keySet()) {
			res.put(s, TopLevelRamlModelBuilder.toObject(parameters.get(s)));
		}
		return res;
	}

}
