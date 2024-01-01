import React from "react";
import { SafeAreaView, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { useNavigation } from "@react-navigation/native";

const Start = () => {

    const navigation = useNavigation();
    const goToSignin = () => {
        navigation.navigate("Signin");
    };
    const goToSignup = () => {
        navigation.navigate("Signup");
    };

    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.top}>
                <Text style={styles.logoText}>FLOCK</Text>
            </View>
            <View style={styles.down}>
                <TouchableOpacity style={styles.signin} onPress={goToSignin}>
                    <Text style={styles.buttonText}>로그인</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.signup} onPress={goToSignup}>
                    <Text style={styles.buttonText}>회원가입</Text>
                </TouchableOpacity>
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    top: {
        flex: 7,
        alignItems: "center",
    },
    down: {
        flex: 3,
        alignItems: "center",
    },
    logoText: {
        color: "#20AAE0",
        fontSize: 60,
        textAlign: "center",
        marginTop: 150,
        fontWeight: "400",
    },
    buttonText: {
        textAlign: "center",
        marginTop: 12,
        color: "black",
    },
    signin: {
        height: 50,
        width: 300,
        backgroundColor: "#F2E230",
        borderRadius: 30,
        borderWidth: 2,
        borderColor: "#B3B3AB",
        margin: 10,
        elevation: 10
    },
    signup: {
        height: 50,
        width: 300,
        backgroundColor: "white",
        borderRadius: 30,
        borderWidth: 2,
        borderColor: "#B3B3AB",
        margin: 10,
        elevation: 10
    },
});

export default Start;
