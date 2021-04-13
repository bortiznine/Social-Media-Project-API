<template>
  <article>
    <PostHeader v-bind:post="post"/>
    <div class="flex-row">
      <h3>{{post.title}}</h3>
      <p class="content">{{post.content}}</p>
    </div>
  </article>

</template>

<script>
import PostHeader from "@/components/PostHeader";
export default {
  name: 'Post',
  components: {PostHeader},
  props: {
    post: Object,
  }
}
</script>

<style scoped>
  article {
    margin: 0 auto;
    width: 600px;
  }
  img {
    width: 50px;
    height: auto;
  }
  .flex-row {
    display: flex;
    flex-direction: column;
    justify-content: center;
    background: #CDCDCD;
    margin-bottom: 4em;
    flex: 0 0 50%;
  }
  .content {
    padding: 1em;
  }

</style>
