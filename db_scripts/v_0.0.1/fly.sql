CREATE TABLE IF NOT EXISTS  `air_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `session_id` varchar(100) NOT NULL,
  `data_id` varchar(100) NOT NULL,
  `order_json` longtext NOT NULL,
  `routing_json` longtext NOT NULL,
  `trip_type` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_d_id` (`session_id`,`data_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='order';