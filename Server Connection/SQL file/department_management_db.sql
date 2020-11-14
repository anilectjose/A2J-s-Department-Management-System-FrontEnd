-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2020 at 10:43 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `department_management_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_db`
--

CREATE TABLE `admin_db` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `emailid` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_db`
--

INSERT INTO `admin_db` (`admin_id`, `username`, `password`, `emailid`) VALUES
(1, 'aja', 'aja', 'ajacreations156@gmail.com'),
(2, 'jaleesa', 'jaleejalee', 'jaleesa2468@gmail.com'),
(3, 'akhila', '123', 'akhilavaugustine@gmail.com'),
(5, 'Anilect', 'chinnu', 'cidkaj@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `assignment_db`
--

CREATE TABLE `assignment_db` (
  `assi_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `desc` varchar(200) NOT NULL,
  `file` varchar(100) NOT NULL,
  `url` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignment_db`
--

INSERT INTO `assignment_db` (`assi_id`, `name`, `subject`, `desc`, `file`, `url`) VALUES
(1, 'Anile', 'maths', 'assi', 'IRJET-V5I250.pdf', 'http://192.168.43.77/aja_admin/api/uploads/IRJET-V5I250.pdf'),
(2, 'anilkumar', 'os', 'assi', '2k10.pdf', 'http://192.168.43.77/aja_admin/api/uploads/2k10.pdf'),
(3, 'raju', 'android', 'assignment not u but me dont bother that is morniidhdfhfjf', '2k10.pdf', 'http://192.168.43.77/aja_admin/api/uploads/2k10.pdf'),
(4, 'aa', 'bb', 'cc', 'A1.pdf', 'http://192.168.43.77/aja_admin/api/uploads/A1.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `attendance_db`
--

CREATE TABLE `attendance_db` (
  `att_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `jun` varchar(20) NOT NULL,
  `jul` varchar(20) NOT NULL,
  `aug` varchar(20) NOT NULL,
  `sep` varchar(20) NOT NULL,
  `oct` varchar(20) NOT NULL,
  `nov` varchar(20) NOT NULL,
  `dece` varchar(20) NOT NULL,
  `jan` varchar(20) NOT NULL,
  `feb` varchar(20) NOT NULL,
  `mar` varchar(20) NOT NULL,
  `total` varchar(20) NOT NULL,
  `year` varchar(20) NOT NULL,
  `role_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance_db`
--

INSERT INTO `attendance_db` (`att_id`, `name`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `dece`, `jan`, `feb`, `mar`, `total`, `year`, `role_id`) VALUES
(1, 'ani', '80', '52', '82', '56', '56', '11', '23', '96', '55', '95', '84', '2', '7'),
(2, 'jal', '80', '52', '82', '56', '56', '11', '23', '96', '55', '95', '84', '3', '0'),
(3, 'akhila', '80', '52', '82', '56', '56', '11', '23', '96', '55', '95', '84', '1', '0'),
(6, 'jolect', '20', '40', '40', '', '', '', '', '', '', '', '35', '1', '0'),
(83, 'jagdish kumar', '', '', '', '', '', '', '', '', '', '', '', '2', '123');

-- --------------------------------------------------------

--
-- Table structure for table `complaint_db`
--

CREATE TABLE `complaint_db` (
  `complaint_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `reply` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint_db`
--

INSERT INTO `complaint_db` (`complaint_id`, `number`, `date`, `content`, `reply`) VALUES
(5, 2, '20-12-2019', 'About programs conducted by department ', 'We will conduct a fest on 4th jan');

-- --------------------------------------------------------

--
-- Table structure for table `leave_db`
--

CREATE TABLE `leave_db` (
  `leave_id` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `year` varchar(20) NOT NULL,
  `reg_no` varchar(20) NOT NULL,
  `reason` varchar(500) NOT NULL,
  `no_of_days` varchar(100) NOT NULL,
  `t_result` varchar(20) DEFAULT NULL,
  `result` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `leave_db`
--

INSERT INTO `leave_db` (`leave_id`, `date`, `name`, `year`, `reg_no`, `reason`, `no_of_days`, `t_result`, `result`) VALUES
(13, '01-01-2020', 'jale', '', '', 'fever', '4', '', 'Approved'),
(17, '01-01-2020', 'jkjkle', '', '', 'fever', '4', '', 'Rejected'),
(19, '01-01-2020', 'ani', '', '', 'fever', '4', '', 'Approved'),
(29, '15-09-1998', 'ani', '1', 'mm17', 'fever', '6', NULL, ''),
(32, '', 'Anilect', '3', 'nn10', 'marriage', '5', NULL, ''),
(33, '', 'bbw', '', '', '', '', 'Rejected', ''),
(34, '', 'jjan', '', '', '', '', 'Approved', ''),
(35, '', 'bbw', '2', 'jk0', 'headache', '3', NULL, ''),
(36, '189', 'Anile', '1st', 'ahjaj', 'angane onnumilla', '2', 'Approved', ''),
(40, '12/12/2000', 'Shahi', 'teacher', 't15', 'Nothing', '50', 'Approved', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `mark_db`
--

CREATE TABLE `mark_db` (
  `mark_id` int(11) NOT NULL,
  `st_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `mark` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mark_db`
--

INSERT INTO `mark_db` (`mark_id`, `st_id`, `sub_id`, `mark`) VALUES
(118, 44, 22, '2'),
(119, 44, 28, '10'),
(120, 44, 29, '15'),
(127, 0, 11, '10'),
(128, 0, 27, '1'),
(129, 99, 4, '0'),
(130, 99, 9, ''),
(131, 99, 24, ''),
(132, 99, 25, '95'),
(133, 35, 4, '10'),
(134, 35, 9, ''),
(135, 35, 24, ''),
(136, 35, 25, '20'),
(137, 44, 30, '55'),
(138, 123, 11, ''),
(139, 123, 27, ''),
(140, 0, 4, ''),
(141, 0, 9, ''),
(142, 0, 24, ''),
(143, 0, 25, '');

-- --------------------------------------------------------

--
-- Table structure for table `notification_db`
--

CREATE TABLE `notification_db` (
  `notification_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `to` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification_db`
--

INSERT INTO `notification_db` (`notification_id`, `content`, `to`) VALUES
(7, 'project day on monday', 'student'),
(8, 'hello, welcomes to AJA', 'teacher'),
(9, 'hello,meeting at 11:00', 'teacher'),
(14, 'hello aja, welcomes u', 'student'),
(16, 'sports day hrwi h tyqt4wq9w tq4tuqptqtuqtttq4ytyqytyqqt', 'teacher,student'),
(22, 'hello aja, welcomes u', 'student'),
(23, 'hello', 'teacher'),
(28, 'hello, students call me now', 'student'),
(29, 'hello come tomorrow', 'student'),
(30, 'Hello evergreen', 'student');

-- --------------------------------------------------------

--
-- Table structure for table `role_db`
--

CREATE TABLE `role_db` (
  `role_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `mentor` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role_db`
--

INSERT INTO `role_db` (`role_id`, `name`, `password`, `role`, `mentor`) VALUES
(1, 'aja', 'aja', 'admin', ''),
(2, 'jaleesa', 'jaleejalee', 'admin', ''),
(3, 'akhila', '123', 'admin', ''),
(4, 'Anilect', 'chinnu', 'admin', ''),
(7, 'ani', 'ani', 'student', ''),
(13, 'jalee', 'jaleesa410', 'student', ''),
(14, 'dawn', 'dawn', 'teacher', '1'),
(35, 'anilect', 'chinnu', 'student', ''),
(36, 'shahi', 'shayi', 'teacher', '2'),
(44, 'jol', 'ect', 'student', ''),
(75, 'ambi', 'jose', 'student', ''),
(121, 'jeswin', 'bro', 'teacher', '3'),
(122, 'manju', 'manju', 'teacher', ''),
(123, 'jaghu', 'jagzz', 'student', ''),
(124, 'sad', 'das', 'teacher', '');

-- --------------------------------------------------------

--
-- Table structure for table `semester_db`
--

CREATE TABLE `semester_db` (
  `sem_id` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `semester` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `semester_db`
--

INSERT INTO `semester_db` (`sem_id`, `year`, `semester`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 2, 4),
(5, 3, 5),
(6, 3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `student_db`
--

CREATE TABLE `student_db` (
  `st_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `reg_no` varchar(100) NOT NULL,
  `mark` int(100) NOT NULL,
  `year` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_db`
--

INSERT INTO `student_db` (`st_id`, `name`, `reg_no`, `mark`, `year`, `role_id`) VALUES
(24, 'anil', '1991', 8, 2, 0),
(25, 'Anilect', 'MM17CCSR01', 10, 3, 35),
(26, 'ani', 'mm17', 5, 3, 0),
(31, 'jolect', '1010', 9, 1, 44),
(92, 'ambi', '123', 7, 3, 99),
(151, 'jagdish kumar', 'mm17ccsr03', 0, 2, 123);

-- --------------------------------------------------------

--
-- Table structure for table `stud_detail`
--

CREATE TABLE `stud_detail` (
  `nme` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `stu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `register_no` varchar(20) NOT NULL,
  `image` varchar(200) NOT NULL,
  `image_url` varchar(200) NOT NULL,
  `year` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stud_detail`
--

INSERT INTO `stud_detail` (`nme`, `email`, `mobile`, `gender`, `stu_id`, `role_id`, `register_no`, `image`, `image_url`, `year`) VALUES
('anilect', 'cidkaj@gmail.com', '9946569128', '', 1, 7, 'mm17ccsro1', 'P_20200523_163457_BF.jpg', 'http://192.168.43.77/aja_admin/api/user_photo/P_20200523_163457_BF.jpg', '2'),
('jaleesa', 'jaleesa@gmail.com', '9656622829', 'female', 7, 13, '114', '', '', '3'),
('Dawn Vincent', 'dawn123@gmail.com', '8606742908', '', 8, 14, 'jal115', '', '', '1'),
('Anilect', 'anil@gmail.com', '9656291130', 'Male', 17, 35, 'MM17CCSR01', '', '', '3'),
('shahina', 'shayi@gnail.com', '9048568516', 'female', 18, 36, '916', 'Screenshot_2019-09-15-10-08-30-33.png', 'http://192.168.43.77/aja_admin/api/user_photo/Screenshot_2019-09-15-10-08-30-33.png', '2'),
('akhila', 'anil@gmail.dj', '0909908', 'Male', 19, 37, 'mm17', '', '', '3'),
('jolect', 'jol@gmail.com', '9000', 'Male', 26, 44, '1010', '', '', '1'),
('ambi', 'ambu', '128', 'Male', 81, 99, '123', '$fname', '$furl', '3'),
('Jeswin', 'bro@gmail.com', '123456', 'Male', 103, 121, 'asd', '', '', ''),
('Manju Joseph', 'manjujoseph072@gmail.com', '6282810744', 'female', 104, 122, 'T42', 'IMG_20200527_142015.jpg', 'http://192.168.43.77/aja_admin/api/user_photo/IMG_20200527_142015.jpg', ''),
('jagdish kumar', 'jaghu@gmail.com', '9946569158', 'Male', 105, 123, 'mm17ccsr03', '', '', '2'),
('arjun pp', 'memories24873@gmail.com', '954403064.', 'Male', 106, 124, '12345', '', '', '-');

-- --------------------------------------------------------

--
-- Table structure for table `subject_db`
--

CREATE TABLE `subject_db` (
  `sub_id` int(11) NOT NULL,
  `sub_name` varchar(100) NOT NULL,
  `year` varchar(20) NOT NULL,
  `sem_id` varchar(10) NOT NULL,
  `tea_id` int(11) NOT NULL,
  `mentor` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject_db`
--

INSERT INTO `subject_db` (`sub_id`, `sub_name`, `year`, `sem_id`, `tea_id`, `mentor`) VALUES
(4, 'web technology', '3', '5', 14, '1'),
(9, 'software engineering', '3', '6', 36, '2'),
(11, 'System Software', '2', '3', 36, ''),
(22, 'c', '1', '2', 14, ''),
(24, 'cpp', '3', '6', 121, '3'),
(25, 'aaaaaa', '3', '5', 121, ''),
(27, 'data mining', '2', '4', 14, ''),
(28, 'blaaa', '1', '2', 7, ''),
(29, 'abcdefg', '1', '1', 14, ''),
(30, 'new sub1', '1', '1', 122, ''),
(31, 'data mining', '3', '6', 121, '');

-- --------------------------------------------------------

--
-- Table structure for table `timetable_db`
--

CREATE TABLE `timetable_db` (
  `tt_id` int(11) NOT NULL,
  `monday` varchar(50) NOT NULL,
  `tuesday` varchar(50) NOT NULL,
  `wednesday` varchar(50) NOT NULL,
  `thursday` varchar(50) NOT NULL,
  `friday` varchar(50) NOT NULL,
  `sem` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `timetable_db`
--

INSERT INTO `timetable_db` (`tt_id`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `sem`) VALUES
(13, 'abcdefg', 'new sub1', 'abcdefg', 'new sub1', 'abcdefg', '1'),
(14, 'new sub1', 'abcdefg', 'new sub1', 'abcdefg', 'new sub1', '1'),
(15, 'abcdefg', 'new sub1', 'abcdefg', 'new sub1', 'abcdefg', '1'),
(16, 'new sub1', 'abcdefg', 'new sub1', 'abcdefg', 'new sub1', '1'),
(17, 'abcdefg', 'new sub1', 'abcdefg', 'new sub1', 'abcdefg', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_db`
--
ALTER TABLE `admin_db`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `assignment_db`
--
ALTER TABLE `assignment_db`
  ADD PRIMARY KEY (`assi_id`);

--
-- Indexes for table `attendance_db`
--
ALTER TABLE `attendance_db`
  ADD PRIMARY KEY (`att_id`);

--
-- Indexes for table `complaint_db`
--
ALTER TABLE `complaint_db`
  ADD PRIMARY KEY (`complaint_id`);

--
-- Indexes for table `leave_db`
--
ALTER TABLE `leave_db`
  ADD PRIMARY KEY (`leave_id`);

--
-- Indexes for table `mark_db`
--
ALTER TABLE `mark_db`
  ADD PRIMARY KEY (`mark_id`);

--
-- Indexes for table `notification_db`
--
ALTER TABLE `notification_db`
  ADD PRIMARY KEY (`notification_id`);

--
-- Indexes for table `role_db`
--
ALTER TABLE `role_db`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `semester_db`
--
ALTER TABLE `semester_db`
  ADD PRIMARY KEY (`sem_id`);

--
-- Indexes for table `student_db`
--
ALTER TABLE `student_db`
  ADD PRIMARY KEY (`st_id`);

--
-- Indexes for table `stud_detail`
--
ALTER TABLE `stud_detail`
  ADD PRIMARY KEY (`stu_id`);

--
-- Indexes for table `subject_db`
--
ALTER TABLE `subject_db`
  ADD PRIMARY KEY (`sub_id`);

--
-- Indexes for table `timetable_db`
--
ALTER TABLE `timetable_db`
  ADD PRIMARY KEY (`tt_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_db`
--
ALTER TABLE `admin_db`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `assignment_db`
--
ALTER TABLE `assignment_db`
  MODIFY `assi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `attendance_db`
--
ALTER TABLE `attendance_db`
  MODIFY `att_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `complaint_db`
--
ALTER TABLE `complaint_db`
  MODIFY `complaint_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `leave_db`
--
ALTER TABLE `leave_db`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `mark_db`
--
ALTER TABLE `mark_db`
  MODIFY `mark_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT for table `notification_db`
--
ALTER TABLE `notification_db`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `role_db`
--
ALTER TABLE `role_db`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT for table `semester_db`
--
ALTER TABLE `semester_db`
  MODIFY `sem_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student_db`
--
ALTER TABLE `student_db`
  MODIFY `st_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

--
-- AUTO_INCREMENT for table `stud_detail`
--
ALTER TABLE `stud_detail`
  MODIFY `stu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT for table `subject_db`
--
ALTER TABLE `subject_db`
  MODIFY `sub_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `timetable_db`
--
ALTER TABLE `timetable_db`
  MODIFY `tt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
