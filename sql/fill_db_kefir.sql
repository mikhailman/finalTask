USE `kefir`;

INSERT INTO `kefir`.`users`(`id`, `role`, `login`, `password`, `email`, `phone`, `name`, `surname`, `status`,
                            `date_registration`)
VALUES (DEFAULT, 1, 'admin', 'password', 'admin@mail.ru', '375292020327', 'valera', 'valerin', DEFAULT, '2019-11-14');
INSERT INTO `kefir`.`users`(`id`, `role`, `login`, `password`, `email`, `phone`, `name`, `surname`, `status`,
                            `date_registration`)
VALUES (DEFAULT, 2, 'user1', 'password', 'user1@mail.ru', '375292020328', 'katya', 'katina', DEFAULT, DEFAULT);
INSERT INTO `kefir`.`users`(`id`, `role`, `login`, `password`, `email`, `phone`, `name`, `surname`, `status`,
                            `date_registration`)
VALUES (DEFAULT, 2, 'user2', 'password', 'user2@mail.ru', '375292020329', 'lera', 'lerina', DEFAULT, DEFAULT);
INSERT INTO `kefir`.`users`(`id`, `role`, `login`, `password`, `email`, `phone`, `name`, `surname`, `status`,
                            `date_registration`)
VALUES (DEFAULT, 2, 'user3', 'password', 'user3@mail.ru', '375292020320', 'nina', 'ninina', DEFAULT, DEFAULT);
INSERT INTO `kefir`.`category` (`id`, `name`)
VALUES (DEFAULT, 'Бытовая техника');
INSERT INTO `kefir`.`category` (`id`, `name`)
VALUES (DEFAULT, 'Крупная бытовая техника');
INSERT INTO `kefir`.`category` (`id`, `name`)
VALUES (DEFAULT, 'Мелкая бытовая техника');
INSERT INTO `kefir`.`location` (`id`, `name`, `city`)
VALUES (DEFAULT, 'Nezavisimosti,4', 'Minsk');
INSERT INTO `kefir`.`location` (`id`, `name`, `city`)
VALUES (DEFAULT, 'Orlovskaya,19', 'Pinsk');
INSERT INTO `kefir`.`products` (`id`, `name`, `description`, `price`, `date_creation`, `users_id`, `category_id`,
                                `location_id`)
VALUES (DEFAULT, 'table', 'my table description', 11.1, '2019-11-14', 15, 2, 1);
INSERT INTO `kefir`.`products` (`id`, `name`, `description`, `price`, `date_creation`, `users_id`, `category_id`,
                                `location_id`)
VALUES (DEFAULT, 'phone', 'my phone description', 33.3, '2019-11-14', 16, 3, 2);
INSERT INTO `kefir`.`images` (`id`, `name`, `image_path`, `products_id`)
VALUES (DEFAULT, 'table_image',
        'https://www.google.by/search?q=стол+фото&client=opera&hs=b3b&sxsrf=ACYBGNT-QCVEDmNz1mGANfFTZkziN3WBCg:1573734183969&tbm=isch&source=iu&ictx=1&fir=K-CJyuN6XssemM%253A%252C9TW2I9_CBvKbJM%252C_&vet=1&usg=AI4_-kSGySRNI_9oigFov9rEKHB0s_CMWA&sa=X&ved=2ahUKEwjYnYbY2OnlAhVhlosKHbcyCwMQ9QEwAHoECAcQBA#imgrc=K-CJyuN6XssemM:',
        13);
INSERT INTO `kefir`.`images` (`id`, `name`, `image_path`, `products_id`)
VALUES (DEFAULT, 'phone_image',
        'https://www.google.by/search?q=телефон+фото&client=opera&sxsrf=ACYBGNTUofwGX72SHK-zoHCFeIIbTkpLAg:1573734718226&tbm=isch&source=iu&ictx=1&fir=c5Gbijx0SiGfTM%253A%252CaNWqGzRLrYhaDM%252C_&vet=1&usg=AI4_-kTJ4PmXpEy30O1clKW7Z6xFZTQ_bA&sa=X&ved=2ahUKEwjv2ObW2unlAhVGAxAIHaAdBI4Q9QEwCnoECAYQGA#imgrc=c5Gbijx0SiGfTM:',
        13);
INSERT INTO `kefir`.`comments` (`id`, `name`, `body`, `users_id`, `products_id`)
VALUES (DEFAULT, 'table_comment', 'this table bla-bla', 16, 12);
INSERT INTO `kefir`.`comments` (`id`, `name`, `body`, `users_id`, `products_id`)
VALUES (DEFAULT, 'phone_comment', 'this phone bla-bla', 15, 13);
