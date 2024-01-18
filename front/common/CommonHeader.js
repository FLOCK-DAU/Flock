// CommonHeader.js
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import BackButtonHeaderLeft from './BackButtonHeaderLeft.js'; // Use forward slash

const CommonHeader = ({ title }) => {
  return (
    <View style={styles.headerContainer}>
      <BackButtonHeaderLeft />
      <Text style={styles.headerTitle}>Flock</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  headerContainer: {
    flexDirection: 'Columm',
    paddingHorizontal: 16,
    paddingVertical: 8,
  },
  headerTitle: {
    marginTop: 10,
    color:"#20AAE0",
    marginLeft: 8,
    fontSize: 40,
  },
});

export default CommonHeader;
