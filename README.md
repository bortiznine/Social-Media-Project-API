# Social-Media-Project

<h1 style=color:teal>Social Media</h1>
<h2 style="color: darkgray">EndPoints</h2>
<h3>

| Request Type | URL| Request Body | Request Header |
|--|--|--|--|
| GET |  /api/posts| None | Authorization Bearer TOKEN|
| GET|/api/posts/{postId}|None |Authorization Bearer TOKEN|
| GET| /api/posts/{postId}/comments|None|Authorization Bearer TOKEN|
| POST| /api/posts | Post postObject|Authorization Bearer TOKEN|
| POST| /api/posts/{postId}/comments | Comment commentObject|Authorization Bearer TOKEN|
| POST| /api/posts/{postId}/reactions/{reaction} | none|Authorization Bearer TOKEN|
| PUT| /api/posts/{postId} | Post postObject|Authorization Bearer TOKEN|
| PUT| /posts/{postId}/comments/{commentId} | Comment commentObject|Authorization Bearer TOKEN|
| DELETE| /api/posts | none |Authorization Bearer TOKEN|
| DELETE| /api/posts/{postId} | none|Authorization Bearer TOKEN|
| DELETE| /api/posts/{postId}/comments/{commentId} | none|Authorization Bearer TOKEN|
| DELETE| /api/posts/{postId}/comments | none|Authorization Bearer TOKEN|
| POST | /auth/users/register | User userObject | none |
| POST | /auth/users/login | LoginRequest loginRequestObject | none |
| PUT | /auth/users/passwordreset | PasswordReset passwordResetObject | none|

</h3>
<h2 style="color: darkgray">Features</h2>
<ul>
  <li>Able to Get Posts, Comments, Reactions and Username</li>
  <li>Able to create a user account with email and password with a profile picture</li>
  <li>Able to reset password for a user</li>
  <li>Able to leave reactions (Like, Laugh,Sad,Angry) on your own and other users posts</li>
  <li>Able to edit Posts And Comments </li>
  <li>Able to delete Posts and Comments</li>
  <li>Built a Front End in Vue on a separate Repository (https://github.com/bortiznine/SocialMediaProjectFrontEnd). The FrontEndFinal branch of this repo is compatible with the Front End repo.</li>
  <li>Unit tests are in the unit-tests branch of this repo</li>
  <li>Able to write a post that is max 1000 characters</li>
  
</ul>

<h2 span style="color: darkgray">ERD Diagram</h2>
<img src="https://github.com/bortiznine/Social-Media-Project-API/blob/main/20210414_165730.jpg">
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

4/13/21:
Early in the session we implemented the password reset. Allowing us to change password for any user. Made a new branch focused on dabbling with front prototype for later build another time.
Created a whole new repo for the front named SOCAILMEDIAFRONTEND. had posts mvp display for vue localhost. Added Reactions model, repo, and implemented the method in socialmediaservice to allow users to react to any post.

4/14/21:
Added a limiter to reactions so users could not react more than once on any given post. Changed character length to 1000 for posts. Added Vue Front end to display backend content. 

4/15/21:
Added a couple of unit tests and smoke tests.
</p>
<br>
<h2 style=color:goldenrod>Reasons</h2>
<p>
4/11/21:
To implement a proper bearer token authentication for our social media database. Also to ensure a secure access thru information to only those with usernames passwords and profiles.
  

4/12/21:
To ensure that a proper social media database can protect other peoples info and posts/comments. thus allowing users to not worry of breach of security.

4/13/21:
If a user wanted to change a password they can now do so. Made sure to allow users the freedom to react to others posts. Having a test frontend prototype can make viewing more accessible to users.

4/14/21:
Limiter ensured users couldn't react twice or more. Used a Vue front-end so we can view backend more easily. Vue also makes api more user-friendly.

4/15/21:
We included tests to ensure that certain methods are working properly.
</p>
<br>
<h2 style=color:red>What Went Wrong</h2>
<p>
4/11/21:
Cannot fully run the server as we have been getting (Unable to start embedded tomcat). We are still missing methods and callbacks to our service class but we hope to see something by end of session today.

4/12/21:
Initially Posts and comments were only seen and accessed to the original user. no other posts or comments were able to be displayed to interact with. Posts and comments were not displaying username effectively to the comments or posts shown (we could not know who was the submitter.)
@Requestbody was missing in a method for social-media controller causing us issues to display proper information.
columns for posts and comments were not organized to be user-friendly.

4/13/21:
REACTIONS WAS A COMPLICATED OBSERVATION! We spent about 3 hours trying to see how we can properly implement such a system for users to have. It's easy on idea but in execution we stumbled on meeting the right parameters.
Seeing reactionsCount work but with no restriction is something we would have to address next meet up.

4/14/21:
We had a misspelled api url link for reacting to posts ('post' is what it was prior). Spacing for title "TWOTTER" for front end was not vertically centered reviewed with Thiago on issue and was resolved.

4/15/21:
When doing unit tests on objects that were retrieved from a repository, their properities, specifically the date, would be formatted slightly differently. A JSON formatter helped with that.
</p>
<br>
<h2 style="color: goldenrod">Challenges We Faced</h2>
<p>
4/11/21:
Reorganizing names of classes to unify order of structure for the project. Making sure we connect a Pgadmin database to the spring boot restAPI.

4/12/21:
reorganizing the code to ensure that users can only edit their own posts and comments. Make sure no hackers got in. 

4/13/21:
Figuring out how to not have users react more than once to a post. A solution must be met. Getting password was thought to be challenge as we expected it to be more endearing to get data passed properly but proved to be simple.

4/14/21:
Organizing and setting parameters for Vue front end. needed to get count of reactions displayed properly.

</p>
<br>
<h2 style="color: limegreen">What To Follow Up With Next Meet Up</h2>
<p>
4/11/21:
Follow up with Suresh and or Marc regarding tomcat embedded issue. Make sure postman routes can be delivered appropriately.Comment entity for model.

4/12/21:
Need to add Reactions and reset password for users. Having those to boot with would make a full-fledged social media project.

4/13/21:
Check how we can limit reaction amount per user on a given post. Try expanding backend to vue front and meet together to make a running full stack site. Updating branches for FrontEnd and master to allow front end use and to keep backend unaffected.

4/14/21:
Last minute testing

</p>
