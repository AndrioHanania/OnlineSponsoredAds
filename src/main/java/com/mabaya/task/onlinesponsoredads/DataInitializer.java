package com.mabaya.task.onlinesponsoredads;

import com.mabaya.task.onlinesponsoredads.campaign.Campaign;
import com.mabaya.task.onlinesponsoredads.campaign.CampaignRepository;
import com.mabaya.task.onlinesponsoredads.category.Category;
import com.mabaya.task.onlinesponsoredads.category.CategoryRepository;
import com.mabaya.task.onlinesponsoredads.product.Product;
import com.mabaya.task.onlinesponsoredads.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;
    private final CategoryRepository categoryRepository;

    private List<Category> categories;
    private List<Product> products;
    private List<Campaign> campaigns;

    private final Function<Integer, String> createCategoryName;
    private final Function<Integer, String> createProductName;
    private final Function<Integer, String> createCampaignName;

    private final Random random;

    private static final int NUM_OF_CATEGORIES = 4;
    private static final int NUM_OF_PRODUCTS = 60;
    private static final int NUM_OF_CAMPAIGNS = 6;

    public DataInitializer(
            ProductRepository productRepository,
            CampaignRepository campaignRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.categoryRepository = categoryRepository;

        categories = new ArrayList<>();
        products = new ArrayList<>();
        campaigns = new ArrayList<>();

        createCategoryName = i -> String.format("category%d", i);
        createProductName = i -> String.format("product%d", i);
        createCampaignName = i -> String.format("campaign%d", i);

        random = new Random();
    }

    @Override
    public void run(String... args) {
        try{
            creatingCategories();
            creatingProducts();
            creatingCampaigns();
        }catch (Exception e){
            log.error("Error in DataInitializer. e=" + e.getMessage());
        }
    }

    public void creatingCategories(){
        if(categoryRepository.count() == 0) {
            for (int i = 1; i <= NUM_OF_CATEGORIES; i++) {
                Long categoryId = Long.valueOf(i);
                int finalI = i;
                categories.add(
                        categoryRepository
                                .findById(categoryId)
                                .orElseGet(() ->
                                        categoryRepository.save(
                                                new Category(createCategoryName.apply(finalI)))));
            }
        }
    }

    public void creatingProducts(){
        if(productRepository.count() == 0) {
            for (int i = 1; i <= NUM_OF_PRODUCTS; i++) {
                Long productId = Long.valueOf(i);
                int finalI = i;
                products.add(
                        productRepository
                                .findById(productId)
                                .orElseGet(() ->
                                        productRepository.save(
                                                new Product(
                                                        finalI,
                                                        createProductName.apply(finalI),
                                                        getRandomCategory(),
                                                        getRandomPrice()))));
            }
        }
    }

    public void creatingCampaigns(){
        if(campaignRepository.count() == 0) {
            for (int i = 1; i <= NUM_OF_CAMPAIGNS; i++) {
                Long campaignId = Long.valueOf(i);
                int finalI = i;
                campaigns.add(
                        campaignRepository
                                .findById(campaignId)
                                .orElseGet(() ->
                                        campaignRepository.save(
                                                new Campaign(
                                                        createCampaignName.apply(finalI),
                                                        getRandomBid(),
                                                        getRandomDate(),
                                                        getRandomProducts())
                                        )));
            }
        }
    }

    public Category getRandomCategory(){
        return categories.get(random.nextInt(NUM_OF_CATEGORIES));
    }

    public Product getRandomProduct(){
        return products.get(random.nextInt(NUM_OF_PRODUCTS));
    }

    public Set<Product> getRandomProducts(){
        Set<Product> products1 = new HashSet<>();
        int size = random.nextInt(6);

        while (size > 0) {
            if (products1.add(getRandomProduct()))
                size--;
        }

        return products1;
    }

    public LocalDate getRandomDate() {
        double probability = random.nextDouble();

        // 70% chance for dates within the current 10 days
        if (probability <= 0.7) {
            LocalDate currentDate = LocalDate.now();
            int randomDays = random.nextInt(10);
            return currentDate.minusDays(randomDays);
        } else {
            // 30% chance for dates before 10 days ago
            int randomDaysBefore = random.nextInt(5) + 1;
            return LocalDate.now().minusDays(10 + randomDaysBefore);
        }
    }

    public double getRandomPrice(){
        return formatDouble((90.00 * random.nextDouble()) + 10.00);
    }

    public double getRandomBid(){
        return formatDouble((100.00 * random.nextDouble()) + 100.00);
    }

    public double formatDouble(double d){
        return Double.parseDouble(String.format("%.2f", d));

    }
}
