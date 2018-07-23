package org.emoflon.ibex.tgg.operational.csp.constraints.factories.ibextggfamiliestopersons;

import java.util.HashMap;
import java.util.HashSet;			

import org.emoflon.ibex.tgg.operational.csp.constraints.factories.RuntimeTGGAttrConstraintFactory;			


public class UserDefinedRuntimeTGGAttrConstraintFactory extends RuntimeTGGAttrConstraintFactory {

	public UserDefinedRuntimeTGGAttrConstraintFactory() {
		super();
	}
	
	@Override
	protected void initialize() {
		creators = new HashMap<>();

		constraints = new HashSet<String>();
		constraints.addAll(creators.keySet());
	}
}
