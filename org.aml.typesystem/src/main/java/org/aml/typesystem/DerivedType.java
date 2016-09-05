package org.aml.typesystem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.aml.typesystem.meta.TypeInformation;
import org.aml.typesystem.meta.facets.internal.ANDRestricton;
import org.aml.typesystem.meta.restrictions.AbstractRestricton;
import org.aml.typesystem.meta.restrictions.KnownPropertyRestricton;

public abstract class DerivedType extends AbstractType {

	protected final LinkedHashSet<AbstractType> options = new LinkedHashSet<>();

	protected DerivedType(String name, AbstractType[] options) {
		super(name);
		for (final AbstractType o : options) {
			this.options.add(o);
		}
	}

	public final Set<AbstractType> allOptions() {
		final LinkedHashSet<AbstractType> results = new LinkedHashSet<>();
		final LinkedHashSet<DerivedType> visited = new LinkedHashSet<>();
		fillOptions(results, visited);
		return results;
	}

	protected abstract TypeInformation createRestricton(AbstractRestricton[] rs);

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DerivedType other = (DerivedType) obj;
		if (options == null) {
			if (other.options != null) {
				return false;
			}
		} else if (!options.equals(other.options)) {
			return false;
		}
		return true;
	}

	@Override
	protected void fillDependencies(LinkedHashSet<AbstractType> ts) {
		super.fillDependencies(ts);
		for (final AbstractType t : this.options) {
			addPotentialDependency(ts, t);
		}
	}

	private void fillOptions(LinkedHashSet<AbstractType> results, LinkedHashSet<DerivedType> visited) {
		for (final AbstractType t : this.options()) {
			if (t==null){
				continue;
			}
			if (t.getClass() == this.getClass()) {
				if (!visited.contains(this)) {
					visited.add(this);
					((DerivedType) t).fillOptions(results, visited);
				}
			} else {
				results.add(t);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (options == null ? 0 : options.hashCode());
		return result;
	}

	@Override
	public Set<TypeInformation> meta() {
		final Set<TypeInformation> result = super.meta();
		final LinkedHashSet<AbstractRestricton> rs = new LinkedHashSet<>();
		for (final AbstractType t : this.options()) {
			if (t==null){
				continue;
			}
			final Set<AbstractRestricton> meta2 = t.restrictions();
			final ArrayList<AbstractRestricton> filtered = new ArrayList<>();
			KnownPropertyRestricton current = null;
			for (final AbstractRestricton r1 : meta2) {
				if (r1 instanceof KnownPropertyRestricton) {
					if (current == null) {
						current = (KnownPropertyRestricton) r1;
					} else {
						final KnownPropertyRestricton knownPropertyRestricton = (KnownPropertyRestricton) r1;
						if (current.base().allSubTypes().contains(knownPropertyRestricton.base())) {
							current = knownPropertyRestricton;
						} else if (!knownPropertyRestricton.base().allSubTypes().contains(current.base())) {
							filtered.add(r1);
						}
					}
				} else {
					filtered.add(r1);
				}
			}
			if (current != null) {
				filtered.add(current);
			}
			rs.add(new ANDRestricton(filtered.toArray(new AbstractRestricton[filtered.size()])));
		}
		result.add(createRestricton(rs.toArray(new AbstractRestricton[rs.size()])));
		return result;
	}

	public Set<AbstractType> options() {
		return new LinkedHashSet<>(this.options);
	}

}