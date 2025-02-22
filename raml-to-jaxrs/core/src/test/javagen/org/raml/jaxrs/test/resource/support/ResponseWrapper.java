
package org.raml.jaxrs.test.resource.support;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Link.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Response wrapper for JAX-RS 2.0
 */
public abstract class ResponseWrapper extends Response
{
    private final Response delegate;

    protected ResponseWrapper(final Response delegate)
    {
        this.delegate = delegate;
    }

    protected static ResponseBuilder headers(final Map<String, List<Object>> headers, final ResponseBuilder responseBuilder)
    {
        if (headers != null)
        {
            for (final Entry<String, List<Object>> nameAndValues : headers.entrySet())
            {
                for (final Object value : nameAndValues.getValue())
                {
                    responseBuilder.header(nameAndValues.getKey(), value);
                }
            }
        }

        return responseBuilder;
    }

    @Override
    public int hashCode()
    {
        return delegate.hashCode();
    }

    @Override
    public int getStatus()
    {
        return delegate.getStatus();
    }

    @Override
    public StatusType getStatusInfo()
    {
        return delegate.getStatusInfo();
    }

    @Override
    public boolean equals(final Object obj)
    {
        return delegate.equals(obj);
    }

    @Override
    public Object getEntity()
    {
        return delegate.getEntity();
    }

    @Override
    public <T> T readEntity(final Class<T> entityType)
    {
        return delegate.readEntity(entityType);
    }

    @Override
    public <T> T readEntity(final GenericType<T> entityType)
    {
        return delegate.readEntity(entityType);
    }

    @Override
    public <T> T readEntity(final Class<T> entityType, final Annotation[] annotations)
    {
        return delegate.readEntity(entityType, annotations);
    }

    @Override
    public String toString()
    {
        return delegate.toString();
    }

    @Override
    public <T> T readEntity(final GenericType<T> entityType, final Annotation[] annotations)
    {
        return delegate.readEntity(entityType, annotations);
    }

    @Override
    public boolean hasEntity()
    {
        return delegate.hasEntity();
    }

    @Override
    public boolean bufferEntity()
    {
        return delegate.bufferEntity();
    }

    @Override
    public void close()
    {
        delegate.close();
    }

    @Override
    public MediaType getMediaType()
    {
        return delegate.getMediaType();
    }

    @Override
    public Locale getLanguage()
    {
        return delegate.getLanguage();
    }

    @Override
    public int getLength()
    {
        return delegate.getLength();
    }

    @Override
    public Set<String> getAllowedMethods()
    {
        return delegate.getAllowedMethods();
    }

    @Override
    public Map<String, NewCookie> getCookies()
    {
        return delegate.getCookies();
    }

    @Override
    public EntityTag getEntityTag()
    {
        return delegate.getEntityTag();
    }

    @Override
    public Date getDate()
    {
        return delegate.getDate();
    }

    @Override
    public Date getLastModified()
    {
        return delegate.getLastModified();
    }

    @Override
    public URI getLocation()
    {
        return delegate.getLocation();
    }

    @Override
    public Set<Link> getLinks()
    {
        return delegate.getLinks();
    }

    @Override
    public boolean hasLink(final String relation)
    {
        return delegate.hasLink(relation);
    }

    @Override
    public Link getLink(final String relation)
    {
        return delegate.getLink(relation);
    }

    @Override
    public Builder getLinkBuilder(final String relation)
    {
        return delegate.getLinkBuilder(relation);
    }

    @Override
    public MultivaluedMap<String, Object> getMetadata()
    {
        return delegate.getMetadata();
    }

    @Override
    public MultivaluedMap<String, Object> getHeaders()
    {
        return delegate.getHeaders();
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders()
    {
        return delegate.getStringHeaders();
    }

    @Override
    public String getHeaderString(final String name)
    {
        return delegate.getHeaderString(name);
    }
}
