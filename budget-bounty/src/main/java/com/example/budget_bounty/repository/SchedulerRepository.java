package com.example.budget_bounty.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.budget_bounty.util.DatabaseConnection;

import model1.Scheduler;

public class SchedulerRepository {
	
	//admin methods
	public List<Scheduler> getAllScheduledPayments() {
        List<Scheduler> schedulers = new ArrayList<>();
        String query = "SELECT * FROM Scheduler";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Scheduler scheduler = mapResultSetToScheduler(resultSet);
                schedulers.add(scheduler);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedulers;
    }
	
	// Method to delete a scheduled bill by ID
    public void deleteScheduler(int schedulerId) {
        String query = "DELETE FROM Scheduler WHERE scheduler_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, schedulerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method to map ResultSet to Scheduler object
    private Scheduler mapResultSetToScheduler(ResultSet resultSet) throws SQLException {
        return new Scheduler(
            resultSet.getInt("scheduler_id"),
            resultSet.getInt("user_id"),
            resultSet.getString("bill_type"),
            resultSet.getInt("customer_id"),
            resultSet.getString("bill_name"),
            resultSet.getString("payee_acc"),
            resultSet.getDouble("amount"),
            resultSet.getDate("due_date"),
            resultSet.getDate("scheduled_date"),
            resultSet.getInt("is_recurring"),
            resultSet.getString("frequency"),
            resultSet.getInt("end_after"),
            resultSet.getDate("end_by"),
            resultSet.getInt("is_paid")
        );
    }
    
    // user methods
    
    //method to get scheduled bills for a specific user using userID
    public List<Scheduler> getSchedulersByUserId(int userId) {
        List<Scheduler> schedulers = new ArrayList<>();
        String query = "SELECT * FROM Scheduler WHERE user_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Scheduler scheduler = mapResultSetToScheduler(resultSet);
                    schedulers.add(scheduler);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedulers;
    }
    
    // method to get a scheduled bill for a user by bill name
    public List<Scheduler> getSchedulersByBillName(int userId, String billName) {
        List<Scheduler> schedulers = new ArrayList<>();
        String query = "SELECT * FROM Scheduler WHERE user_id = ? AND bill_name = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, billName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Scheduler scheduler = mapResultSetToScheduler(resultSet);
                    schedulers.add(scheduler);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedulers;
    }
    
    // adding a new scheduled bill
    public void addScheduler(Scheduler scheduler) {
        String query = "INSERT INTO Scheduler (scheduler_id, user_id, bill_type, customer_id, bill_name, payee_acc, amount, due_date, scheduled_date, is_recurring, frequency, end_after, end_by, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        	preparedStatement.setInt(1, scheduler.getSchedulerId());
            preparedStatement.setInt(2, scheduler.getUserId());
            preparedStatement.setString(3, scheduler.getBillType());
            if (scheduler.getCustomerId() != null) {
                preparedStatement.setInt(4, scheduler.getCustomerId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(5, scheduler.getBillName());
            preparedStatement.setString(6, scheduler.getPayeeAcc());
            preparedStatement.setDouble(7, scheduler.getAmount());
            preparedStatement.setDate(8, scheduler.getDueDate());
            preparedStatement.setDate(9, scheduler.getScheduledDate());
            preparedStatement.setInt(10, scheduler.isRecurring());
            if (scheduler.getFrequency() != null) {
                preparedStatement.setString(11, scheduler.getFrequency());
            } else {
                preparedStatement.setNull(11, java.sql.Types.VARCHAR);
            }
            if (scheduler.getEndAfter() != null) {
                preparedStatement.setInt(12, scheduler.getEndAfter());
            } else {
                preparedStatement.setNull(12, java.sql.Types.INTEGER);
            }

            // Handle possible null for endBy
            if (scheduler.getEndBy() != null) {
                preparedStatement.setDate(13, scheduler.getEndBy());
            } else {
                preparedStatement.setNull(13, java.sql.Types.DATE);
            }
            preparedStatement.setInt(14, scheduler.isPaid());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to update an existing scheduled bill
    public void updateScheduler(Scheduler scheduler) {
        String query = "UPDATE Scheduler SET user_id = ?, bill_type = ?, customer_id = ?, bill_name = ?, payee_acc = ?, amount = ?, due_date = ?, scheduled_date = ?, is_recurring = ?, frequency = ?, end_after = ?, end_by = ?, is_paid = ? WHERE scheduler_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, scheduler.getUserId());
            preparedStatement.setString(2, scheduler.getBillType());
            preparedStatement.setInt(3, scheduler.getCustomerId());
            preparedStatement.setString(4, scheduler.getBillName());
            preparedStatement.setString(5, scheduler.getPayeeAcc());
            preparedStatement.setDouble(6, scheduler.getAmount());
            preparedStatement.setDate(7, scheduler.getDueDate());
            preparedStatement.setDate(8, scheduler.getScheduledDate());
            preparedStatement.setInt(9, scheduler.isRecurring());
            preparedStatement.setString(10, scheduler.getFrequency());
            preparedStatement.setInt(11, scheduler.getEndAfter());
            preparedStatement.setDate(12, scheduler.getEndBy());
            preparedStatement.setInt(13, scheduler.isPaid());
            preparedStatement.setInt(14, scheduler.getSchedulerId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //printing all scheduled bills in the db
    public void printAllScheduledPayments() {
        String query = "SELECT * FROM Scheduler";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                
                // Retrieve data from resultSet and process it
                int schedulerId = resultSet.getInt("scheduler_id");
                int userId = resultSet.getInt("user_id");
                String billType = resultSet.getString("bill_type");
                int customerId = resultSet.getInt("customer_id");
                String billName = resultSet.getString("bill_name");
                String payeeAcc = resultSet.getString("payee_acc");
                double amount = resultSet.getDouble("amount");
                java.sql.Date dueDate = resultSet.getDate("due_date");
                java.sql.Date scheduledDate = resultSet.getDate("scheduled_date");
                int isRecurring = resultSet.getInt("is_recurring");
                String frequency = resultSet.getString("frequency");
                int endAfter = resultSet.getInt("end_after");
                java.sql.Date endBy = resultSet.getDate("end_by");
                int isPaid = resultSet.getInt("is_paid");

                // Print the retrieved data
                System.out.println("Scheduler ID: " + schedulerId);
                System.out.println("User ID: " + userId);
                System.out.println("Bill Type: " + billType);
                System.out.println("Customer ID: " + customerId);
                System.out.println("Bill Name: " + billName);
                System.out.println("Payee Account: " + payeeAcc);
                System.out.println("Amount: " + amount);
                System.out.println("Due Date: " + dueDate);
                System.out.println("Scheduled Date: " + scheduledDate);
                System.out.println("Is Recurring: " + isRecurring);
                System.out.println("Frequency: " + frequency);
                System.out.println("End After: " + endAfter);
                System.out.println("End By: " + endBy);
                System.out.println("Is Paid: " + isPaid);
                System.out.println("----------------------------");
            }

            if (!hasResults) {
                System.out.println("No scheduled payments found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
