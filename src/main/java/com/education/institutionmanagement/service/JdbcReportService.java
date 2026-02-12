package com.education.institutionmanagement.service;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdbcReportService {

    private final DataSource dataSource;

    public JdbcReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Map<String, Object>> getInstitutionStudentCounts() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT i.name as institution_name, COUNT(s.id) as student_count " +
                     "FROM institutions i " +
                     "LEFT JOIN students s ON i.id = s.institution_id " +
                     "GROUP BY i.id, i.name";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("institutionName", rs.getString(1));
                row.put("studentCount", rs.getLong(2));
                results.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing JDBC report", e);
        }
        return results;
    }
}
