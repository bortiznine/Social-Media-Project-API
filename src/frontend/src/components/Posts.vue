<template>
   <div>
      <div v-bind:key="post.id" v-for="post in posts">
        <Post v-bind:post="post" />
      </div>
  </div>
</template>


<script>
import Post from "@/components/Post"

export default {
  name: 'Posts',
  props: {
    posts: Array,
  },
  components: {
    Post,
  },

}
</script>
