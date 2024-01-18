import React from 'react';
import { TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import AntDesign from 'react-native-vector-icons/AntDesign';

const BackButtonHeaderLeft = () => {
  const navigation = useNavigation();

  const handlePress = () => {
    navigation.goBack();
  };

  return (
    <TouchableOpacity onPress={handlePress}>
      <AntDesign name="left" size={20} color="black" />
    </TouchableOpacity>
  );
};

export default BackButtonHeaderLeft;
