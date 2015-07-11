package hk.org.ha.pbrc.dashboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

public abstract class MeasurementServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "application/json";
    
    @SuppressWarnings("compatibility:-3105493193219135707")
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {        
        response.setContentType(CONTENT_TYPE);        
        
        String isCR = request.getParameter("isCR");
        
        PrintWriter out = response.getWriter();
        Vector result;
        if (isCR !=null && isCR.equals("Y")){
            result = getResultCR(request);         
        }
        else {
            result = getResult(request);
        }
            
        String jsonString = toString(result);
        out.write(jsonString);
    }
    
    protected abstract Vector getResult(HttpServletRequest request);    
    protected abstract Vector getResultCR(HttpServletRequest request);        
    protected abstract String toString(Vector vec);

}
