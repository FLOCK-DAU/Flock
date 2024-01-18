// Signin.js
import React from "react";
import { SafeAreaView, StyleSheet, Text, View } from "react-native";
import CommonHeader from '../../common/CommonHeader';  
const Signin = () => {
    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.header}>
                <CommonHeader title="Flock" />
            </View>
            <View style={styles.top}>
                <Text style={styles.text}>Signin Screen</Text>
            </View>
        </SafeAreaView>
    );
};


const styles = StyleSheet.create({
    container: {
        flex: 1,

        justifyContent: "center",
    },
    top: {
        flex: 1,
        alignItems: "center",
        justifyContent: "center",
    },
    text: {
        fontSize: 20,
    },
});

export default Signin;
