<template>
    <header class="flex">
      <img class="profile-pic" v-bind:src="post.user.profilePicture"  alt=""/>
      <p class="username">{{ post.user.username }}</p>
      <p class="date">Date posted: {{ post.publishDate }}</p>
    </header>
</template>

<script>
export default {
  name: 'UserInfo',
  props: {
    profilePicUrl: {
      type: String,
      default: "https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png",
    },
    post: Object
  }
}
</script>


<style scoped>
  header {
    display: flex;
    flex-direction: row;
    justify-content: center;
    background: #EEE;
  }
  header > * {
    margin-right: 20px;
  }
  img.profile-pic {
    width: 50px;
    height: auto;
  }
  .username {
    font-weight: 600;
    color: #4d73e8;
  }
</style>
