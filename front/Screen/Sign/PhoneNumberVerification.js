import React, { useState } from "react";
import {
    SafeAreaView,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    TextInput,
    Modal,
    DatePickerAndroid,
} from "react-native";
import { Picker } from '@react-native-picker/picker';
import CommonHeader from "../../common/CommonHeader";
import { useNavigation } from "@react-navigation/native";
import CheckBox from '@react-native-community/checkbox';
import DateTimePicker from '@react-native-community/datetimepicker';



const PhoneNumberVerification = () => {
    const [name, setName] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [gender, setGender] = useState("");
    const [phoneNum, setPhoneNum] = useState("");
    const [certificationNum, setCertificationNum] = useState("");

    // State variables for checkboxes
    const [isModalVisible, setModalVisible] = useState(false);
    const [isCheckedTerms, setCheckedTerms] = useState(false);
    const [isCheckedPersonalInfo, setCheckedPersonalInfo] = useState(false);
    const [isCheckedCommunityGuidelines, setCheckedCommunityGuidelines] = useState(false);
    const [isCheckedNotifications, setCheckedNotifications] = useState(false);
    const [isCheckedAll, setCheckedAll] = useState(false);

    const navigation = useNavigation();

    const handleSignIn = () => {
        console.log("Email:", email);
        console.log("Password:", password);
    };

    const toggleModal = () => {
        setModalVisible(!isModalVisible);
    };

    // Function to handle registration
    const handleRegistration = () => {
        // Perform registration logic here
        console.log("Registration completed!");
        // You may want to navigate to the next screen or perform other actions
    };

    // Function to handle master checkbox
    const handleMasterCheckbox = () => {
        setCheckedAll(!isCheckedAll);
        setCheckedTerms(!isCheckedAll);
        setCheckedPersonalInfo(!isCheckedAll);
        setCheckedCommunityGuidelines(!isCheckedAll);
        setCheckedNotifications(!isCheckedAll);
    };
    const handleCloseModal = () => {
        setModalVisible(false);
    };

    const showDatePicker = async () => {
        try {
          const { action, year, month, day } = await DateTimePicker.open({
            date: new Date(),
            maxDate: new Date(), // Prevent future dates
          });
    
          if (action !== DateTimePicker.dismissedAction) {
            // Selected year, month (0-11), and day.
            setBirthdate(`${year}-${month + 1}-${day}`);
          }
        } catch ({ code, message }) {
          console.warn("Cannot open date picker", message);
        }
      };
    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.header}>
                <CommonHeader title="Flock" />
                <View
                    style={{
                        justifyContent: "center",
                        alignItems: "center",
                        margin: 30,
                    }}
                >
                    <Text
                        style={{
                            fontWeight: "500",
                            fontSize: 20,
                            color: "black",
                        }}
                    >
                        What do you do in your free time?{" "}
                    </Text>
                    <Text
                        style={{
                            fontWeight: "300",
                            fontSize: 15,
                            color: "black",
                        }}
                    >
                        Have a blast!!
                    </Text>
                </View>
            </View>

            <View style={styles.top}>
                <View style={styles.label}>
                    <Text style={styles.label}>전화번호를 인증해 주세요</Text>
                    <Text style={styles.label2}>
                        신뢰할 수 있는 커뮤니티를 위해 전화번호 인증이 필요해요.
                    </Text>
                </View>
                <View style={styles.label}>
                    <Text style={styles.label}>이름</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="홍길동"
                    value={name}
                    onChangeText={(text) => setName(text)}
                />
                {/* Birthdate input field */}
                <View style={styles.label}>
  <Text style={styles.label}>생년월일</Text>
</View>
<DateTimePicker
  style={styles.input}
  value={new Date()} // You may want to replace this with the actual date value
  mode="date"
  display="default"
  onChange={(event, selectedDate) => {
    // Handle date change
    console.log(selectedDate);
  }}
/>

        {/* Gender dropdown */}
        <View style={styles.label}>
          <Text style={styles.label}>성별</Text>
        </View>
        <Picker
          style={styles.input}
          selectedValue={gender}
          onValueChange={(itemValue) => setGender(itemValue)}
        >
          <Picker.Item label="남성" value="남성" />
          <Picker.Item label="여성" value="여성" />
        </Picker>

                <View style={styles.label}>
                    <Text style={styles.label}>전화번호</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="010-1234-5678"
                    value={phoneNum}
                    onChangeText={(text) => setPhoneNum(text)}
                />

                <View style={styles.label}>
                    <Text style={styles.label}>인증번호</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="인증번호를 입력해주세요"
                    value={certificationNum}
                    onChangeText={(text) => setCertificationNum(text)}
                />

                <TouchableOpacity onPress={toggleModal}>
                    <View
                        style={{
                            margin: 10,
                            backgroundColor: "#A5CDEB",
                            justifyContent: "center",
                            alignItems: "center",
                            borderRadius: 100,
                            paddingVertical: 10,
                        }}
                    >
                        <Text
                            style={{
                                color: "white",
                                fontSize: 20,
                            }}
                        >
                            확인{" "}
                        </Text>
                    </View>
                </TouchableOpacity>

                {/* Modal for Terms and Conditions Agreement */}
                <Modal animationType="slide" transparent={true} visible={isModalVisible}>
                    <View style={styles.modalContainer}>
                        <View style={styles.modalContent}>
                             {/* Close button (X) */}
                        <TouchableOpacity onPress={handleCloseModal} style={styles.closeButton}>
                            <Text style={{ fontSize: 24, color: "black" }}>X</Text>
                        </TouchableOpacity>
                            <View style={styles.checkboxContainer}>
                                <CheckBox
                                    value={isCheckedAll}
                                    onValueChange={handleMasterCheckbox}
                                />
                                <Text>전체 동의</Text>
                            </View>

                            {/* Checkboxes for terms and conditions */}
                            <CheckBox
                                value={isCheckedTerms}
                                onValueChange={() => setCheckedTerms(!isCheckedTerms)}
                            />
                            <Text>서비스 이용약관 동의 </Text>

                            <CheckBox
                                value={isCheckedPersonalInfo}
                                onValueChange={() =>
                                    setCheckedPersonalInfo(!isCheckedPersonalInfo)
                                }
                            />
                            <Text>개인정보 수집 및 이용 동의</Text>

                            <CheckBox
                                value={isCheckedCommunityGuidelines}
                                onValueChange={() =>
                                    setCheckedCommunityGuidelines(!isCheckedCommunityGuidelines)
                                }
                            />
                            <Text>커뮤니티 가이드 동의</Text>

                            <CheckBox
                                value={isCheckedNotifications}
                                onValueChange={() => setCheckedNotifications(!isCheckedNotifications)}
                            />
                            <Text>혜택 및 이벤트 알림 수신 동 </Text>

                            {}
                            <TouchableOpacity onPress={handleRegistration}>
                                <View style={styles.modalButton}>
                                    <Text style={styles.modalButtonText}>가입 완료하고 시작하기</Text>
                                </View>
                            </TouchableOpacity>
                        </View>
                    </View>
                </Modal>
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#ffffff",
    },
    header: {
        flex: 3,
        borderBottomRightRadius: 200,
        backgroundColor: "#A5CDEB",
    },
    top: {
        flex: 7,
        justifyContent: "center",
        backgroundColor: "#fff",
        paddingHorizontal: 30,
        borderTopRightRadius: 60,
    },
    text: {
        fontSize: 20,
    },
    input: {
        height: 40,
        margin: 5,
        borderRadius: 10,
        backgroundColor: "#e7e7e7",
        padding: 10,
        fontSize: 12,
    },
    label: {
        paddingLeft: 5,
        fontSize: 17,
        color: "#6E6E6E",
    },
    label2: {
        fontWeight: "300",
        paddingLeft: 5,
        fontSize: 10,
        color: "#999",
    },
    modalContainer: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
    modalContent: {
        backgroundColor: "white",
        padding: 20,
        borderRadius: 10,
        elevation: 5,
    },
    modalButton: {
        backgroundColor: "#A5CDEB",
        justifyContent: "center",
        alignItems: "center",
        borderRadius: 100,
        paddingVertical: 10,
        marginTop: 10,
    },
    modalButtonText: {
        color: "white",
        fontSize: 16,
    },
    checkboxContainer: {
        flexDirection: "row",
        alignItems: "center",
    },
});

export default PhoneNumberVerification;