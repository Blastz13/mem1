<template>
  <div class="cart__product-item">
    <img :src="imgUrl" style="width: 100px;">
    <div class="product-item__description">
      <div class="product-item__info">
        <router-link :to="{name: 'ProductView', params: {id: product.id}}">
          <h3>{{ product.title }}</h3>
        </router-link>
      </div>

      <div class="product__counter">
        <span @click="deleteProductInCart()">&#8211;</span>
        <span>{{ product.quantity }} </span>
        <span @click="addProductInCart()">+</span>
      </div>

      <div class="product-item__price">
        <h3 v-if="product.discount">{{ product.new_price }}₽</h3>
        <h3 v-if="!product.discount">{{ product.price }}₽</h3>
      </div>
      <div class="product-item__action">
        <img src="@/assets/delete.svg" style="width: 30px; height: 30px" @click="removeProductFromCart">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CartProductItem",
  data() {
    return {
      imgUrl: "https://ih1.redbubble.net/image.343726250.4611/flat,1000x1000,075,f.jpg"
    }
  },
  props: {
    product: {

    }
  },
  mounted() {
    if (this.product.images && this.product.images.length > 0) {
      this.imgUrl = "http://localhost:8081/image/" + this.product.images[0].url
    }
  },
  methods: {
    addProductInCart() {
      this.$store.commit('changeQuantityProductInCart', {payload: this.product, increase: true});
    },
    deleteProductInCart() {
      this.$store.commit('changeQuantityProductInCart', {payload: this.product, increase: false});
    },
    removeProductFromCart() {
      this.$store.commit('removeProductFromCart', this.product);
    }
  },
}
</script>

<style lang="scss" scoped>
.product-item__description h3 {
  margin: 0;
}

.cart__product-item {
  display: flex;
  border-bottom: 1px solid rgba(200, 200, 200, 1);
  padding: 10px;
}

.cart__product-item:last-child {
  border: none;
}

.product-item__action {
  width: 20px;
  height: 20px;
}

.product-item__description {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: flex-start;
  width: 100%;
  margin-left: 20px;
}

.product__counter {
  display: flex;
  cursor: pointer;
  justify-content: space-between;
  border-radius: 10px;
  width: 80px;
}

.product__counter span {
  padding: 5px;
  color: black;
  font-weight: bold;
  font-size:24px;
}
</style>