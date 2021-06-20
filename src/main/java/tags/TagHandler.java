package tags;

import dao.JdbcUserDao;
import dao.UserDao;
import model.User;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


public class TagHandler extends TagSupport {

    private final UserDao userDao;

    public TagHandler() {
        userDao = new JdbcUserDao();
    }

    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        StringBuilder table = new StringBuilder();
        List<User> users = userDao.findAll();
        table.append("<table class=\"table table-striped table-hover\">");
        table.append("<thead>\n" );
        table.append("<tr>\n" );
        table.append("<th>FirstName</th>\n");
        table.append("<th>LastName</th>\n" );
        table.append("<th>Age</th>\n" );
        table.append("<th>Actions</th>\n" );
        table.append("</tr>\n" );
        table.append("</thead>\n" );
        table.append("<tbody>" );

        for (User user : users) {
            table.append("<tr>");
            table.append("<td>" + user.getFirstName() + "</td>");
            table.append("<td>" + user.getLastName() + "</td>");
            table.append("<td>" + Period.between(user.getBirthDate(), LocalDate.now()).getYears() + "</td>");
            table.append("<td>\n");
            table.append("<a href=\"/users/editUser?id=" + user.getId().toString() + "\" class=\"edit\">Edit </a>\n");
            table.append("<a href=\"#deleteUserModal\" data-id=" + user.getId() + " class=\"open-DeleteUserModal delete\" data-toggle=\"modal\">Delete </a>\n");
            table.append("</td>\n");
            table.append("</tr>");
        }

        table.append("</tbody>\n");
        table.append("</table>");

        try{
            out.write(table.toString());
        } catch(IOException e){
            System.out.println(e);
            throw new RuntimeException("Invalid user table");
        }

        return SKIP_BODY;
    }
}
