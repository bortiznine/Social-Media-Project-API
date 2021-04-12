<h1 span style=color:teal>Social Media</h1>
<br>
<h2 span style=color:goldenrod>Design Decisions</h2>
<p>
4/11/21:
We added Post Comment models. Joined two columns for both post and comment to user_id. Cleaned up main portions of the social media service. We put the comment routes in the social media controller. Added classes JWTRequestFilter, JWTUtils, MyUserDetails, MyUserDetailService and UserService.

4/12/21:
Added parameters to make sure Posts Comments and users worked effectively to be able to give the proper authorizations to their own account.
Edited api to ensure (ex. user1 cannot affect posts and comments of user2 etc.) to ensure safety and privacy.
Made all posts public so even non users can see posts, but will be required to have an account to see comments and the sort.
users can now comment on other users posts but still cannot affect others posts.
</p>
<br>
<h2 span style=color:goldenrod>Reasons</h2>
<p>
4/11/21:
To implement a proper bearer token authentication for our social media database. Also to ensure a secure access thru information to only those with usernames passwords and profiles.

4/12/21:
To ensure that a proper social media database can protect other peoples info and posts/comments. thus allowing users to not worry of breach of security.
</p>
<br>
<h2 span style=color:red>What Went Wrong</h2>
<p>
4/11/21:
Cannot fully run the server as we have been getting (Unable to start embedded tomcat). We are still missing methods and callbacks to our service class but we hope to see something by end of session today.

4/12/21:
Initially Posts and comments were only seen and accessed to the original user. no other posts or comments were able to be displayed to interact with. Posts and comments were not displaying username effectively to the comments or posts shown (we could not know who was the submitter.)
@requestbody was missing in a method for social-media controller causing us issues to display proper information.
columns for posts and comments were not organized to be user-friendly.
</p>
<br>
<h2 span style="color: goldenrod">Challenges We Faced</h2>
<p>
4/11/21:
Reorganizing names of classes to unify order of structure for the project. Making sure we connect a Pgadmin database to the spring boot restAPI.

4/12/21:
reorganizing the code to ensure that users can only edit their own posts and comments. Make sure no hackers got in. 
</p>
<br>
<h2 span style="color: limegreen">What To Follow Up With Next Meet Up</h2>
<p>
4/11/21:
Follow up with Suresh and or Marc regarding tomcat embedded issue. Make sure postman routes can be delivered appropriately.Comment entity for model.

4/12/21:
Need to add Reactions and reset password for users. Having those to boot with would make a full-fledged social media project.
</p>
