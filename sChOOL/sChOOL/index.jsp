<%@page import="Beans.UserBean"%>
<%UserBean userBean = new UserBean();%>
<%@page import="DAO.UserDAO"%>
<%UserDAO userDAO = new UserDAO();%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Carousel Template for Bootstrap</title>
	
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/mystyle.css" rel="stylesheet">
    <link href="css/carousel.css" rel="stylesheet">
    <script src="js/ie10-viewport-bug-workaround.js"></script>



    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="css/carousel.css" rel="stylesheet">
  </head>
<!-- NAVBAR
================================================== -->


  <body>
  
    <!-- Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Login Page</h4>
      </div>
        <form name="loginModal" action="UserLogin" method="post">
      <div class="modal-body">
           
        <div class="container">
                <p>Username: <input id="userId" type="text" name="userId" maxlength="30"></p>
                <p>Password: <input id="password" type="password" name="password" maxlength="30"></p>
        </div>

      </div>
      <div class="modal-footer">
           <input type="submit" class="btn btn-primary" value="Sign In >>">
      </div>
        </form>
    </div>
  </div>
</div>
    <div class="navbar navbar-inverse">
        <div class="container">

 			<ul class="nav nav-pills pull-right">
                <li>
                	<button class="btn btn-primary" data-toggle="modal"  data-target="#loginModal" aria-pressed="false" autocomplete="off">
                    Login</button>
                </li>
          	</ul>
  <h3>St. Michael's Learning Center</h3>
      </div>
</div>

<!--    <div class="navbar navbar-fixed-top" role="navigation">
        <div class="container">
            <ul class="nav nav-pills pull-right">
                <li><a href="#">Login</a><li>
          </ul>
        </div>
    </div>  -->


 <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">


      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img src="http://p4cdn4static.sharpschool.com/UserFiles/Servers/Server_35702/Image/news%20pictures/back-to-school.jpg" alt="First slide">
          <div class="container">
             <div class="carousel-caption">
              <h1>Welcome Back To School.</h1>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
            </div> 
          </div>
        </div>
        <div class="item">
          <img src="http://www.psd150.org//cms/lib2/il01001530/centricity/domain/1358/school%20news.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>News and Announcement Below!</h1>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="http://schoolweb.tdsb.on.ca/portals/kingsview/images/kindergarten-day.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Explore the World of Children.</h1>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->
<div class="school-announcements">
<div class="container">
<pre> Announcement Page 
    <%=userDAO.selectAnnouncement()%></pre>
</div>
</div>
    <div class="school-info-buttons">
      <div class="container">
   
      <div class="row">

                  <div class="col-sm-3 col-md-3">
                          <div class="aa-buttons">
                        <div class="thumbnail">
                         <a href="about-us.jsp"><img src="http://premier.us/sites/all/themes/premier/images/about-us_pages_banner.png" height="320" width="250"></img></a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3 col-md-3">
                          <div class="aa-buttons">
                        <div class="thumbnail">
                         <a href="accreditation.jsp"><img src="http://www.blake-school.org/Common/Images/General/ChildPage/accreditation.jpg" height="320" width="250"></img></a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3 col-md-3">
                          <div class="fc-buttons">
                        <div class="thumbnail">
                         <a href="facilities.jsp"><img src="http://www.brookfieldparkcpschool.co.uk/userimages/School%20Facilities.jpg" height="320" width="250"></img></a>
                        </div>
                    </div>
                </div>

                <div class="col-sm-3 col-md-3">
                          <div class="fc-buttons">
                        <div class="thumbnail">
                         <a href="contact.jsp"><img src="http://www.lsmuedu.com/wp-content/uploads/2013/11/contact-us-banner.jpg" height="320" width="250"></img></a>
                        </div>
                    </div>
                </div>

            </div>
          </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
