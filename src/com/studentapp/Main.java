package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Student> studentList;
	private static Scanner scanner;

	public static void main(String[] args) {
		System.out.println("********** Student Management System **********");

		studentList = new ArrayList<Student>();

		scanner = new Scanner(System.in);

		while (true) {

			System.out.println("******************* Welcome *******************");

			System.out.println("Select any one of the below Options!");
			System.out.println("1. Register a Student");
			System.out.println("2. Find Student with studentId");
			System.out.println("3. List all Student Information");
			System.out.println("4. List Student Information in Sorted Order");
			System.out.println("5. Exit");

			int option = scanner.nextInt();

			switch (option) {

			case 1:
				enrollStudent(scanner);
				break;

			case 2:
				findStudentById(scanner);
				break;

			case 3:
				printAllStudentData();
				break;

			case 4:
				sortByName();
				break;

			case 5:
				exit();
				break;

			default:
				System.out.println("Please enter valid option...Enter between 1 to 5");
			}
		}
	}

	private static void exit() {
		
		System.out.println("Thanks for using the Application!!!");
		System.exit(0);

	}

	private static void printAllStudentData() {

		if (studentList.size() > 0) {
			System.out.println("---------------- PRINT ALL STUDENT DATA ----------------");
			for (Student student : studentList) {
				student.printStudentInfo();
			}

			System.out.println("--------------------------------------------------------");
		} else {
			System.err.println("Student List is Empty! No Student Record Found");
		}

	}

	private static void enrollStudent(Scanner scanner2) {

		System.out.println("Enter Student Name:");
		String studentName = scanner2.next();

		System.out.println("Enter Student Age:");
		int studentAge = scanner2.nextInt();

		System.out.println("Enter Student ID:");
		String studentId = scanner2.next();

		Student newStudent = new Student(studentName, studentAge, studentId);
		studentList.add(newStudent);

		while (true) {
			System.out.println("Enter the course to be enrolled!! Type 'Done' to exit");
			String courseName = scanner2.next();
			if (courseName.equalsIgnoreCase("done")) {
				break;
			}
			newStudent.enrollCourse(courseName);
		}
		newStudent.printStudentInfo();
	}

	private static void sortByName() {
		Comparator<Student> studentNameComparator = (o1, o2) -> o1.getName()
				.compareTo(o2.getName()); /*
											 * = new Comparator<Student>() {
											 * 
											 * @Override public int compare(Student o1, Student o2) { return
											 * o1.getName().compareTo(o2.getName());// Comparison will happen
											 * Lexogrphically } };
											 */
		Collections.sort(studentList, studentNameComparator);
		printAllStudentData();
	}

	public static void findStudentById(Scanner scanner2) {

		System.out.println("Search Student ID:");

		String studentId = scanner2.next();

		Student studentFound = null; // local varaible -> explicitely initialize (Non-primitive data type have a
								// default value as null)
		try {
			studentFound = studentList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("No Data Found"));
		} catch (RuntimeException e) {
			System.err.println("Student with ID " + studentId + " is not found");
		}
		studentFound.printStudentInfo();
	}

}
