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

    const navigateToSignup = () => {
        navigation.navigate('Signup');
    };
    const navigateToFindAccount = () => {
        navigation.navigate('FindAccount');
    };

    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.header}>
                <CommonHeader title="Flock" />
                <View style={{
                        justifyContent: 'center',
                        alignItems: 'center',
                        margin:30
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
                    <Text style={styles.label}>Username</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="Enter your email"
                    value={email}
                    onChangeText={(text) => setEmail(text)}
                />

                <View style={styles.label}>
                    <Text style={styles.label}>Password</Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder="Enter your password"
                    secureTextEntry
                    value={password}
                    onChangeText={(text) => setPassword(text)}
                />
                <TouchableOpacity>
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
                            }}>Login </Text>
                    </View>

                </TouchableOpacity>
                <View style={styles.mod}>
                <TouchableOpacity onPress={navigateToFindAccount}>
                    <Text>계정 정보를 잊으셨나요?</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={navigateToSignup}>
                    <Text>회원가입 하기</Text>
                </TouchableOpacity>
                </View>
                <Text> </Text>
                <Text> </Text>
                <Text> </Text>
                <Text> </Text>
                <Text> </Text>
                <Text> </Text>
                <Text> </Text>
                <Text style={{color:"gray"}}>가입을 진행할 경우,<Text style={{color:"black", fontWeight:"900"}}> 서비스 약관</Text>및 <Text style={{color:"black", fontWeight:"900"}}>개인정보 처리방침</Text>에 동의한 것으로 간주합니다. </Text>
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
    },
    label: {
        fontWeight: '300',
        paddingLeft: 5,
        fontSize: 17,
        color: '#999',
    },
    mod:{
        flexDirection:"row",
        justifyContent:"space-between"
    }
});

export default Signin;
