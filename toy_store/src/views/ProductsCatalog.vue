<template>
  <UpButtonPage/>
  <div class="">
    <div class="container">
      <div class="category_breadcrumb">
        <ul class="breadcrumb">
          <li><a href="#">Главная</a></li>
          <li><a href="#">Каталог</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="">
    <div class="container">
      <div class="products-catalog__title">
        <h2>Рюкзаки</h2>
        <select name="sort_by_field" class="sort_product_select">
          <option value="price">Цена</option>
          <option value="rating">Рейтинг</option>
          <option value="count">Количество</option>
          <option value="popular">Популярность</option>
        </select>
      </div>
      <div class="products__article">
        <div class="products-catalog__filter">
          <div class="filter__category">
            <h4 style="margin-top: 0px;">Категории</h4>
            <ul>
              <li v-for="category in categoryList" :key="category.id">
                <router-link :to="{name: 'ProductsCatalog', params: {id: category.id}}">
                  {{ category.title }}
                </router-link>
              </li>
            </ul>
          </div>
          <div class="filter__price-range" style="border-top: 1px solid grey;">
            <h4>Цена</h4>
            <div class="price-range">
              <div class="filter__price-from">
                <p class="filter__price-label">от</p>
                <input class="filter__price-left" type="number" v-model="filter.filterPriceLeft">
              </div>
              <div class="filter__price-from">
                <p class="filter__price-label">до</p>
                <input class="filter__price-right" type="number" v-model="filter.filterPriceRight">
              </div>
            </div>
          </div>
          <div class="filter__delivery" style="border-top: 1px solid grey;">
            <h4>Период доставки</h4>
            <ul>
              <li>
                <input type="radio" id="deliveryChoice0" name="delivery" value="" v-model="filter.delivery">
                <label for="deliveryChoice0">не важно</label>
              </li>
              <li>
                <input type="radio" id="deliveryChoice1" name="delivery" value="1" v-model="filter.delivery">
                <label for="deliveryChoice1">1 день</label>
              </li>
              <li>
                <input type="radio" id="deliveryChoice2" name="delivery" value="2" v-model="filter.delivery">
                <label for="deliveryChoice2">2 день</label>
              </li>
              <li>
                <input type="radio" id="deliveryChoice3" name="delivery" value="3" v-model="filter.delivery">
                <label for="deliveryChoice3">до 3 дней</label>
              </li>
            </ul>
          </div>
          <div class="filter__sale" style="border-top: 1px solid grey;">
            <h4>Скидка</h4>
            <ul>
              <li>
                <input type="radio" id="saleChoice0" name="contact" value="" v-model.number="filter.discount">
                <label for="saleChoice0">не важно</label>
              </li>
              <li>
                <input type="radio" id="saleChoice0" name="contact" :value=10 v-model.number="filter.discount">
                <label for="saleChoice1">скидка 10%</label>
              </li>
              <li>
                <input type="radio" id="saleChoice2" name="contact" :value=30 v-model.number="filter.discount">
                <label for="saleChoice2">скидка 30%</label>
              </li>
              <li>
                <input type="radio" id="saleChoice3" name="contact" :value=50 v-model.number="filter.discount">
                <label for="saleChoice3">скидка 50%</label>
              </li>
            </ul>
          </div>
        </div>
        <div class="product-catalog__grip">
          <ProductCard v-for="product in productsList" :key="product.id" :product="product" class="product__grid"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UpButtonPage from "@/components/UpButtonPage";
import ProductCard from "@/components/ProductCard";

export default {
  name: "ProductsCatalog",
  components: {ProductCard, UpButtonPage},
  data() {
    return {
      // productList: [],
      filter:{
        filterPriceLeft: 0,
        filterPriceRight: 0,
        delivery: "",
        discount: "",
      }
      // v-model="message"

    }
  },
  created() {

  },
  watch: {
    '$route.params.id': {
      handler() {
        this.loadProducts();
      }
    },
    'filter': {
      handler() {
        console.log(this.filter)
        this.loadProducts();
      },
      deep: true
    },
  },
  mounted() {
    this.loadProducts();
    this.loadCategories();
  },
  computed: {
    productsList() {
      return this.$store.getters.getProductList;
    },
    categoryList() {
      return this.$store.getters.getCategoryList;
    }
  },
  methods: {
    loadProducts() {
      if (this.$route.params.id) {
        this.$store.dispatch("loadProductsByFiltersId", {id: this.$route.params.id, filters: this.filter});
      } else {
        this.$store.dispatch("loadProductsByFilters", {filters: this.filter});
      }
    },
    loadCategories() {
      this.$store.dispatch("loadCategories");
    },
    // filterPriceLeft(e){
    //   this.$route.params.add
    //   console.log(parseInt(e.target.value));
    // },
    // filterPriceRight(e){
    //   console.log(parseInt(e.target.value));
    // }
  }
}
</script>

<style lang="scss" scoped>

.filter__price-left,
.filter__price-right {
  width: 100%;
  font-size: 20px;
  text-indent: 5px;
  border-right: 1px solid grey;
  height: 35px;
  border-radius: 15px 0px 0px 15px;
}

.filter__price-right {
  border-radius: 0px 15px 15px 0px;
}

.filter__price-label {
  margin: 0px 0px 2px 0px;
  font-size: 15px;
  font-weight: 200;

}

.filter__price-from {
  width: 50%;
}

.price-range {
  display: flex;
}

h4 {
  font-size: 22px;
  margin: 25px 0px 10px 0px;
}

.filter__price-range {
  margin: 10px 0px;
}

.sort_product_select {
  width: 120px;
  height: 30px;
  border: 1px solid #999;
  font-size: 18px;
  font-weight: 500;
  color: #1c87c9;
  background-color: #eee;
  border-radius: 5px;
}

.products-catalog__filter {
  margin-top: 8px;
  padding: 15px;
  border-radius: 15px;
  position: sticky;
  top: 10px;
}

.product__grid {
  width: 32% !important;
  max-width: none;
  min-width: 200px;
  margin: 1% 1% 0px 0px;
}

.products__article {
  display: flex;
  justify-content: space-between;
  align-items: start;
}

.products-catalog__filter {
  min-width: 280px;
  background: #ffffff;

  ul > li > a {
    text-decoration: none;
    color: #999999;
  }
}

.product-catalog__grip {
  display: flex;
  flex-wrap: wrap;
  margin-left: 10px;
  justify-content: space-between;
}

.products-catalog__title {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

ul {
  margin: 0px;
  list-style-type: none;
  padding: 0px;
}

ul > li > label {
  margin: 5px;
}

ul > li {
  font-size: 18px;
  padding: 2px;
  display: flex;
  align-items: center;
}

input[type=radio] {
  width: 20px;
  height: 20px;
}

ul.breadcrumb {
  padding: 0px 16px;
  list-style: none;
}

ul.breadcrumb li {
  display: inline;
  font-size: 18px;
}

ul.breadcrumb li + li:before {
  padding: 8px;
  color: black;
  content: "/\00a0";
}

ul.breadcrumb li a {
  color: #6c7073;
  text-decoration: none;
}

ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}

@media screen and (max-width: 1000px) {
  .product__grid {
    width: 49% !important;
    max-width: none;
    min-width: 200px;
  }
}

@media screen and (max-width: 820px) {
  .product__grid {
    width: 90% !important;
    max-width: 400px;
    min-width: 200px;
    margin: 1% auto;

  }
}

@media screen and (max-width: 600px) {
  .product-catalog__grip {
    margin-left: 0px;
  }
  .products-catalog__filter {
    position: inherit;
    margin-bottom: 10px;
  }

  .products__article {
    flex-wrap: wrap;
  }

  .product__grid {
    width: 48% !important;
    max-width: none;
    min-width: 150px;
    margin: 1% auto;

  }

  .products-catalog__filter {
    width: 100%;
  }
}
</style>