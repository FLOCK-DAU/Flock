// FindAccount.js
import React from "react";
import { SafeAreaView, StyleSheet, Text, View } from "react-native";

const FindAccount = () => {
    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.top}>
                <Text style={styles.text}>FindAccount Screen</Text>
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: "center",
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

export default FindAccount;
