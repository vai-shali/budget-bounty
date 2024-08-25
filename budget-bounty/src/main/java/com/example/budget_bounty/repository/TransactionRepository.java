package com.example.budget_bounty.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.budget_bounty.util.DatabaseConnection;

import model1.Transaction;

public class TransactionRepository {

    public void save(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO Transaction (transaction_id, user_id, transaction_date, transaction_name, " +
                "from_account_number, to_account_number, transaction_type, amount, reference_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, transaction.getTransactionId());
            preparedStatement.setInt(2, transaction.getUserId());
            preparedStatement.setDate(3, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.setString(4, transaction.getTransactionName());
            preparedStatement.setString(5, transaction.getFromAccountNumber());
            preparedStatement.setString(6, transaction.getToAccountNumber());
            preparedStatement.setString(7, transaction.getTransactionType());
            preparedStatement.setDouble(8, transaction.getAmount());
            preparedStatement.setString(9, transaction.getReferenceNumber());

            preparedStatement.executeUpdate();
        }
    }

    //admin functions
    //find by transaction id
    public Transaction findById(int transactionId) throws SQLException {
        String sql = "SELECT * FROM Transaction WHERE transaction_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, transactionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRowToTransaction(resultSet);
            }
        }

        return null; // If no result found
    }

    //get all transactions
    public List<Transaction> findAll() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                transactions.add(mapRowToTransaction(resultSet));
            }
        }

        return transactions;
    }

    // update transaction
    public void update(Transaction transaction) throws SQLException {
        String sql = "UPDATE Transaction SET user_id = ?, transaction_date = ?, transaction_name = ?, " +
                "from_account_number = ?, to_account_number = ?, transaction_type = ?, amount = ?, " +
                "reference_number = ? WHERE transaction_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, transaction.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.setString(3, transaction.getTransactionName());
            preparedStatement.setString(4, transaction.getFromAccountNumber());
            preparedStatement.setString(5, transaction.getToAccountNumber());
            preparedStatement.setString(6, transaction.getTransactionType());
            preparedStatement.setDouble(7, transaction.getAmount());
            preparedStatement.setString(8, transaction.getReferenceNumber());
            preparedStatement.setInt(9, transaction.getTransactionId());

            preparedStatement.executeUpdate();
        }
    }

    // delete a transaction
    public void delete(int transactionId) throws SQLException {
        String sql = "DELETE FROM Transaction WHERE transaction_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, transactionId);
            preparedStatement.executeUpdate();
        }
    }
    
    // user function: view transaction based on user id
    public List<Transaction> findByUserId(int userId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                transactions.add(mapRowToTransaction(resultSet));
            }
        }

        return transactions;
    }
    
    public int getMaxTransactionId() throws SQLException {
        String sql = "SELECT MAX(transaction_id) AS max_id FROM Transaction";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            } else {
                return 0; // No transactions in the table, start with ID 1
            }
        }
    }
    

    private Transaction mapRowToTransaction(ResultSet resultSet) throws SQLException {
        return new Transaction(
                resultSet.getInt("transaction_id"),
                resultSet.getInt("user_id"),
                resultSet.getDate("transaction_date"),
                resultSet.getString("transaction_name"),
                resultSet.getString("from_account_number"),
                resultSet.getString("to_account_number"),
                resultSet.getString("transaction_type"),
                resultSet.getDouble("amount"),
                resultSet.getString("reference_number")
        );
    }
}