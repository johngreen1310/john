import java.lang.Math.*;
import java.util.*;
public class KMeans1D {
Scanner sc = new Scanner(System.in);
int nOfElements, nOfClusters, flag;
int ptr[] = new int[100];
double mean[] = new double[100];
double oldMean[] = new double[100];
static double elements[] = new double[100];
double k[][] = new double[100][100];
void insert() throws InvalidClusterSize {
System.out.print("\nEnter number of Clusters: ");
nOfClusters = sc.nextInt();
System.out.print("\nEnter number of points: ");
nOfElements = sc.nextInt();
if (nOfElements < nOfClusters) {
throw new InvalidClusterSize(
"\nNumber of Elements cannot be less than Clusters!"
);
}
System.out.print("\nEnter points: ");
for (int i = 0; i < nOfElements; i++) {
elements[i] = sc.nextInt();
}
for (int i = 0; i < nOfClusters; i++) {
mean[i] = elements[i];
}
}
void solve() {
int clusterIndex = flag = 0;
double leastDiff, tempDist;
for (int i = 0; i < nOfClusters; i++) {
ptr[i] = 0;
}
for (int i = 0; i < nOfElements; i++) {
leastDiff = Math.abs(elements[i] - mean[0]);
clusterIndex = 0;
for (int j = 0; j < nOfClusters; j++) {
tempDist = Math.abs(elements[i] - mean[j]);
if (tempDist < leastDiff) {
leastDiff = tempDist;
clusterIndex = j;
}
}
k[clusterIndex][ptr[clusterIndex]] = elements[i];
ptr[clusterIndex]++;
}
for (int i = 0; i < nOfClusters; i++) {
mean[i] = 0;
for (int j = 0; j < ptr[i]; j++) {
mean[i] += k[i][j];
}
mean[i] /= ptr[i];
}
for (int i = 0; i < nOfClusters; i++) {
if (oldMean[i] != mean[i]) {
flag = 1;
break;
}
}
if (flag == 1) {
for (int i = 0; i < nOfClusters; i++) {
oldMean[i] = mean[i];
}
solve();
}
}
void disp() {
for (int i = 0; i < nOfClusters; i++) {
System.out.print("\nCluster " + (i + 1) + ":");
for (int j = 0; j < ptr[i]; j++) {
System.out.print("\t" + (int) (k[i][j]));
}
System.out.println("\nMean " + (i + 1) + " = " + mean[i]);
}
System.out.println();
}
public static void main(String args[]) {
try {
KMeans1D km = new KMeans1D();
System.out.println("\n\t\tK-Means 1D Clustering");
km.insert();
km.solve();
km.disp();
} catch (InvalidClusterSize e) {
System.out.println("\nException occured: " + e);
}
}
}
class InvalidClusterSize extends Exception {
public InvalidClusterSize(String s) {
super(s);
}
}