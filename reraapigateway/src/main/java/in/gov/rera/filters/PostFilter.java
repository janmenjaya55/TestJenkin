package in.gov.rera.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {


	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
    if( ctx.getResponseStatusCode()==404)
     ctx.setResponseStatusCode(403);   
     
    return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return  1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return  "post";
	}
}