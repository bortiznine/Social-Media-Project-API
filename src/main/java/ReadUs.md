<h1 span style=color:teal>Social Media</h1>
<br>
<h2 span style=color:goldenrod>Design Decisions</h2>
<p>
4/11/21:
We added Post Comment models. Joined two columns for both post and comment to user_id. Cleaned up main portions of the social media service. We put the comment routes in the social media controller. Added classes JWTRequestFilter, JWTUtils, MyUserDetails, MyUserDetailService and UserService.

</p>
<br>
<h2 span style=color:goldenrod>Reasons</h2>
<p>
4/11/21:
TO implement a proper bearer token authentication for our social media database. Also to ensure a secure access thru information to only those with usernames passwords and profiles.
</p>
<br>
<h2 span style=color:red>What Went Wrong</h2>
<p>
4/11/21:
Cannot fully run the server as we have been getting (Unable to start embedded tomcat). We are still missing methods and callbacks to our service class but we hope to see something by end of session today.
</p>
<br>
<h2 span style="color: goldenrod">Challenges We Faced</h2>
<p>
4/11/21:
Reorganizing names of classes to unify order of structure for the project. Making sure we connect a Pgadmin database to the spring boot restAPI.
</p>
<br>
<h2 span style="color: limegreen">What To Follow Up With Next Meet Up</h2>
<p><
4/11/21:
Follow up with Suresh and or Marc regarding tomcat embedded issue. Make sure postman routes can be delivered appropriately.Comment entity for model.
/p>
