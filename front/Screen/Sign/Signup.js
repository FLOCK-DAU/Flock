import React, { useState } from "react";
import { SafeAreaView, StyleSheet, Text, View, TouchableOpacity, TextInput, Button } from "react-native";
import CommonHeader from '../../common/CommonHeader';
import { useNavigation } from '@react-navigation/native';

const Signin = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigation = useNavigation();

    const handleSignIn = () => {
        console.log('Email:', email);
        console.log('Password:', password);
    };


    const navigateToPhoneNumberVerification = () => {
        navigation.navigate('PhoneNumberVerification');
    };

    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.header}>
                <CommonHeader title="Flock" />
                <View style={{
                    justifyContent: 'center',
                    alignItems: 'center',
                    margin: 30
                }}>
                    <Text style={{
                        fontWeight: '500', fontSize: 20,
                        color: 'black'
                    }}>What do you do in your free time? </Text>
                    <Text style={{
                        fontWeight: '300', fontSize: 15,
                        color: 'black'
                    }}>Have a blast!!</Text>
                </View>
            </View>

            <View style={styles.top}>

                <View style={styles.label}>
                    <Text style={styles.label}>Enter your email</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="example@flock.kr"
                    value={email}
                    onChangeText={(text) => setEmail(text)}
                />

                <View style={styles.label}>
                    <Text style={styles.label}>Enter your password</Text>
                    <Text style={styles.label2}>영문,숫자,특수문자를 조합하여 8자리이상 작성해 주세요.</Text>
                </View>
                <View style={styles.label}>
                    <Text style={styles.label}>비밀번호</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="비밀번호를 입력해주세요"
                    value={email}
                    onChangeText={(text) => setEmail(text)}
                />

                <View style={styles.label}>
                    <Text style={styles.label}>비밀번호 확인</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="비밀번호를 한번 더 입력해주세요"
                    value={email}
                    onChangeText={(text) => setEmail(text)}
                />

                <TouchableOpacity onPress={navigateToPhoneNumberVerification}>
                    <View
                        style={{
                            margin: 10,
                            backgroundColor: '#A5CDEB',
                            justifyContent: 'center',
                            alignItems: 'center',
                            borderRadius: 100,
                            paddingVertical: 10,
                        }}>
                        <Text
                            style={{
                                color: 'white',
                                fontSize: 20
                            }}>다음 </Text>
                    </View>

                </TouchableOpacity>
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#ffffff'
    },
    header: {
        flex: 3,
        borderBottomRightRadius: 200,
        backgroundColor: '#A5CDEB',
    },
    top: {
        flex: 7,
        justifyContent: "center",
        backgroundColor: '#fff',
        paddingHorizontal: 30,
        borderTopRightRadius: 60
    },
    text: {
        fontSize: 20,
    },
    input: {
        height: 40,
        margin: 5,
        borderRadius: 10,
        backgroundColor: '#e7e7e7',
        padding: 10,
        fontSize: 12,
    },
    label: {
        paddingLeft: 5,
        fontSize: 17,
        color: '#6E6E6E',
    },
    label2: {
        fontWeight: '300',
        paddingLeft: 5,
        fontSize: 10,
        color: '#999',
    },
    mod: {
        flexDirection: "row",
        justifyContent: "space-between"
    }
});

export default Signin;
