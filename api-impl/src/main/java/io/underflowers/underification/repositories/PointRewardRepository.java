package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.*;
import io.underflowers.underification.repositories.projections.PairProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PointRewardRepository extends CrudRepository<PointRewardEntity, Long> {
    @Query (value = "SELECT U.app_user_id AS `first`, SUM(R.points) AS `second`\n" +
            "FROM point_reward_entity AS R\n" +
            "INNER JOIN user_entity as U ON R.user_id = U.id\n" +
            "INNER JOIN point_scale_entity AS S ON R.point_scale_id = S.id\n" +
            "WHERE S.name=:scale AND S.application_id=:appId\n" +
            "GROUP BY U.app_user_id\n" +
            "ORDER BY `second` DESC\n" +
            "LIMIT :limit", nativeQuery = true)
    List<PairProjection<String, BigDecimal>> findLeaders(@Param("appId") long appId,
                                                         @Param("scale") String scale,
                                                         @Param("limit") int limit);

    @Query (value ="SELECT S.name AS `first`, SUM(R.points) AS `second`\n" +
            "FROM point_reward_entity AS R\n" +
            "INNER JOIN point_scale_entity AS S ON R.point_scale_id = S.id\n" +
            "WHERE R.user_id=:userId\n" +
            "GROUP BY S.name", nativeQuery = true)
    List<PairProjection<String, BigDecimal>> findScores(@Param("userId") long userId);
}
